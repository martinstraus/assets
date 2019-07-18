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
package assets.investments;

import assets.Assets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

/**
 *
 * @author martinstraus
 */
public class ListPage implements Route {

    private final Assets assets;
    private final TemplateEngine templateEngine;

    public ListPage(Assets assets, TemplateEngine templateEngine) {
        this.assets = assets;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request rqst, Response rspns) throws Exception {
        Map<String, Object> model = new HashMap<>();
        model.put("assets", assets.all());
        return templateEngine.render(new ModelAndView(model, "/investments.html"));
    }

}
