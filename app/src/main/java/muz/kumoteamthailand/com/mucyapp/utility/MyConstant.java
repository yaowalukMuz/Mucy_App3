package muz.kumoteamthailand.com.mucyapp.utility;

public class MyConstant {


//    URL
    private String urlTestString = "http://androidthai.in.th/muz/getTestMuzc.php";
    private String urlAddNewUser = "http://43.229.78.211/sigvip/androidService/addUser.php";
    private String urlReadAllUser = "http://43.229.78.211/sigvip/androidService/getUser.php";


    //    Array
    private String[] column_tm_client = new String[]{
            "client_id",
            "client_name",
            "client_lastname",
            "client_username",
            "client_password",
            "client_email",
            "client_registerDate",
            "client_phone",
            "point_balance",
            "client_pin",
            "pic_profile",
            "pic_path",
            "status",
            "checkCode",
            "createBy",
            "createDate",
            "updateBy",
            "updateDate"
    };


    public String[] getColumn_tm_client() {
        return column_tm_client;
    }

    public String getUrlReadAllUser() {
        return urlReadAllUser;
    }

    public String getUrlAddNewUser() {
        return urlAddNewUser;
    }

    public String getUrlTestString() {
        return urlTestString;
    }
}
