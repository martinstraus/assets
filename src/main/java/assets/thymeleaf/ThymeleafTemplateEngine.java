/*
 * Copyright (C) 2019 martinstraus
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package assets.thymeleaf;

import java.util.Locale;
import java.util.Map;
import org.thymeleaf.context.Context;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import spark.ModelAndView;
import spark.TemplateEngine;

/**
 *
 * @author martinstraus
 */
public class ThymeleafTemplateEngine extends TemplateEngine {

    private static final String DEFAULT_PREFIX = "templates/";
    private static final String DEFAULT_SUFFIX = ".html";
    private static final long DEFAULT_CACHE_TTL_MS = 3600000L;

    private org.thymeleaf.TemplateEngine templateEngine;

    /**
     * Constructs a default thymeleaf template engine.
     * Defaults prefix (template directory in resource path) to templates/ and suffix to .html
     */
    public ThymeleafTemplateEngine() {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX);
    }

    /**
     * Constructs a thymeleaf template engine with specified prefix and suffix
     *
     * @param prefix the prefix (template directory in resource path)
     * @param suffix the suffix (e.g. .html)
     */
    public ThymeleafTemplateEngine(String prefix, String suffix) {
        ITemplateResolver defaultTemplateResolver = createDefaultTemplateResolver(prefix, suffix);
        initialize(defaultTemplateResolver);
    }

    /**
     * Constructs a thymeleaf template engine with a proprietary initialize
     *
     * @param templateResolver the template resolver.
     */
    public ThymeleafTemplateEngine(ITemplateResolver templateResolver) {
        initialize(templateResolver);
    }

    private static ITemplateResolver createDefaultTemplateResolver(String prefix, String suffix) {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);

        templateResolver.setPrefix(
                prefix != null ? prefix : DEFAULT_PREFIX
        );

        templateResolver.setSuffix(
                suffix != null ? suffix : DEFAULT_SUFFIX
        );

        templateResolver.setCacheTTLMs(DEFAULT_CACHE_TTL_MS);
        return templateResolver;
    }

    /**
     * Initializes and sets the template resolver
     */
    private void initialize(ITemplateResolver templateResolver) {
        templateEngine = new org.thymeleaf.TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.addDialect(new Dialect());
    }

    @Override
    @SuppressWarnings("unchecked")
    public String render(ModelAndView modelAndView) {
        return render(modelAndView, Locale.getDefault());
    }

  /**
   * Process the specified template (usually the template name).
   * Output will be written into a String that will be returned from calling this method,
   * once template processing has finished.
   * @param modelAndView model and view
   * @param locale A Locale object represents a specific geographical, political, or cultural region
   * @return processed template
   */
    public String render(ModelAndView modelAndView, Locale locale) {
        Object model = modelAndView.getModel();

        if (model instanceof Map) {
            Context context = new Context(locale);
            context.setVariables((Map<String, Object>) model);
            return templateEngine.process(modelAndView.getViewName(), context);
        } else {
            throw new IllegalArgumentException("modelAndView.getModel() must return a java.util.Map");
        }
    }
}
