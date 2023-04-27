package com.bilgeadam.constants;

public class EndPoints {
    /**
     * Uygulamanız içinde kullanılan tüm erişiö noktalarının listesi burada tutulur,
     * böylece farklı ortamlar i.in kullanılacak end pointler tek bir noktadan değiştirilebilir.
     */
    public static final String VERSION = "/v1";
    public static final String DEV = "/dev";
    public static final String API = "/api";
    public static final String ELASTIC = "/elastic";
    public static final String ADMIN = "/admin";
    public static final String COMPANYMANAGER = "/companymanager";
    public static final String PERSONNEL = "/personnel";

    public static final String SAVE = "/save";
    public static final String UPDATEADMIN = "/updateadmin";
    public static final String UPDATEPERSONNEL = "/updatepersonnel";
    public static final String UPDATECOMPANYMANAGER = "/updatecompanymanager";
    public static final String UPDATECOMPANY= "/updatecompany";
    public static final String CREATECOMPANYMANAGER= "/createcompanymanager";
    public static final String CREATECOMPANY= "/createcompany";
    public static final String CREATEPERSONNEL= "/createpersonnel";

    public static final String DELETE = "/delete";
    public static final String GETALLADMIN = "/getalladmin";
    public static final String GETALL = "/getall";
    public static final String GETALLPAGE = "/getallpage";
    public static final String GETADMINBYID = "/getadminbyid";
    public static final String GETCOMPANYBYID = "/getcompanybyid";
    public static final String GETCOMPANYMANAGERBYID = "/getcompanymanagerbyid";
    public static final String GETPERSONNELBYID = "/getpersonnelbyid";
    public static final String GETBYAD = "/getbyad";
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
}
