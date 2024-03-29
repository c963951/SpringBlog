package com.raysmond.blog.services;

import com.raysmond.blog.services.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Raysmond<i@raysmond.com>
 */
@JadeHelper("App")
@Service
public class AppSetting {

    private SettingService settingService;

    private String siteName = "SpringBlog";
    private String siteSlogan = "An interesting place to discover";
    private Integer pageSize = 5;
    private String storagePath = "/tmp";
    private String mainUri = "http://localhost/";

    public static final String SITE_NAME = "site_name";
    public static final String SITE_SLOGAN = "site_slogan";
    public static final String PAGE_SIZE = "page_size";
    public static final String STORAGE_PATH = "storage_path";
    public static final String MAIN_URI = "main_uri";

    @Autowired
    public AppSetting(SettingService settingService){
        this.settingService = settingService;
    }

    public String getSiteName(){
        return (String) settingService.get(SITE_NAME, siteName);
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
        settingService.put(SITE_NAME, siteName);
    }

    public Integer getPageSize() {
        return (Integer) settingService.get(PAGE_SIZE, pageSize);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        settingService.put(PAGE_SIZE, pageSize);
    }

    public String getSiteSlogan() {
        return (String) settingService.get(SITE_SLOGAN, siteSlogan);
    }

    public void setSiteSlogan(String siteSlogan) {
        this.siteSlogan = siteSlogan;
        settingService.put(SITE_SLOGAN, siteSlogan);
    }

    public String getStoragePath() {
        return (String) settingService.get(STORAGE_PATH, storagePath);
    }

    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;
        settingService.put(STORAGE_PATH, storagePath);
    }

    public String getMainUri() {
        return (String) settingService.get(MAIN_URI, mainUri);
    }

    public void setMainUri(String mainUri) {
        this.mainUri = mainUri;
        settingService.put(MAIN_URI, mainUri);
    }

    public List<String> getOgLocales() {
        ArrayList<String> ogLocales = new ArrayList<>();
        ogLocales.add("en_EN");
        ogLocales.add("ru_RU");
        return ogLocales;
    }

    public List<String> getOgTypes() {
        ArrayList<String> ogTypes = new ArrayList<>();
        ogTypes.add("article");
        return ogTypes;
    }
}
