package com.tu.integrationmaster.pojo.app.settings.config;

import com.tu.integrationmaster.resources.properties.ResourceProperties;

/**
 * @author Canavans
 */
public class IntegrationConfigPOJO {
    public static final IntegrationConfigPOJO INSTANCE = new IntegrationConfigPOJO();
    private final ResourceProperties resourceProperties = new ResourceProperties();

    private final String INPUT_FOLDER = resourceProperties.integrationConfigBundle().getProperty("Inputs");
    private final String OUTPUT_FOLDER = resourceProperties.integrationConfigBundle().getProperty("Outputs");
    private final String MAPPING_FOLDER = resourceProperties.integrationConfigBundle().getProperty("Mappings");
    private final String CERTIFICATES_FOLDER = resourceProperties.integrationConfigBundle().getProperty("Certificates");
    private final String ARCHIVES_FOLDER = resourceProperties.integrationConfigBundle().getProperty("Archives");
    private final String BUSINESS_LOGS_FOLDER = resourceProperties.integrationConfigBundle().getProperty("Logs");


    public String getINPUT_FOLDER() {
        return INPUT_FOLDER;
    }

    public String getOUTPUT_FOLDER() {
        return OUTPUT_FOLDER;
    }

    public String getMAPPING_FOLDER() {
        return MAPPING_FOLDER;
    }

    public String getCERTIFICATES_FOLDER() {
        return CERTIFICATES_FOLDER;
    }

    public String getARCHIVES_FOLDER() {
        return ARCHIVES_FOLDER;
    }

    public String getBUSINESS_LOGS_FOLDER() {
        return BUSINESS_LOGS_FOLDER;
    }
}
