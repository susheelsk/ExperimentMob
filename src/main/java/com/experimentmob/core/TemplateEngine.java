package com.experimentmob.core;


import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 *
 */
public class TemplateEngine
{
    private static final Configuration config;
    
    private static TemplateEngine instance;
    
    static {

        try {
            config = new Configuration();
            config.setDirectoryForTemplateLoading(new File("tpl/"));
            config.setObjectWrapper(new DefaultObjectWrapper());
            config.setDefaultEncoding("UTF-8");
            config.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
            config.setIncompatibleImprovements(new Version(2, 3, 20));
        } catch (Exception e) {
        	e.printStackTrace();
            throw new IllegalStateException("Could not load template files", e);
        }
    
    }
    /**
     * @return template engine
     * @throws AbTestingException if there is error in instantiating
     */
    public static TemplateEngine getInstance() throws AbTestingException {
        if(instance == null) {
            instance = new TemplateEngine();
        }
        return instance;
    }
    
    /**
     * @param tplName name of the template
     * @return rendered template
     * @throws IOException if template is not found
     * @throws TemplateException if template is not valid
     */
    public String render(String tplName) throws IOException, TemplateException {
        Model model = new Model();
        return render(tplName, model);
    }
    
    /**
     * @param tplName template name
     * @param model model to follow
     * @return rendered template
     * @throws IOException if template is not found
     * @throws TemplateException if template is not valid
     */
    public String render(String tplName, Model model) throws IOException, TemplateException {
        Template tpl = config.getTemplate(tplName + ".ftl");
        StringWriter out = new StringWriter();
        tpl.process(model, out);
        return out.toString();
    }
}
