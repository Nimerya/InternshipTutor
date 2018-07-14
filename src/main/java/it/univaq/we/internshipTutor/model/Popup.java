package it.univaq.we.internshipTutor.model;

public class Popup {

    private String type;
    private String message;
    private String code;

    // defaults
    private static final String ERROR = "ERROR";
    private static final String WARNING = "WARNING";
    private static final String SUCCESS = "SUCCESS";

    private static final String CODE = "";

    public static final String ERR_MSG_IT = "Non è stato possibile completare l'operazione!";
    public static final String ERR_MSG_EN = "The operation cannot be completed!";

    public static final String WAR_MSG_IT = "Qualcosa è andato storto!";
    public static final String WAR_MSG_EN = "Something went wrong!";
    public static final String WAR_MSG_EN_SAVE = "Something went wrong while aplying your changes, one or more of the fields could be duplicated.";


    public static final String SUCC_MSG_IT = "Operazione eseguita con successo!";
    public static final String SUCC_MSG_EN = "Operation completed successfully!";



    public Popup(){
        type = SUCCESS;
        message = SUCC_MSG_EN;
        code = CODE;
    }

    public Popup(String type, String message, String code){
        this.type = type.toUpperCase();
        this.code = code;
        this.message = message+"\nCode: "+code;

    }

    public Popup(String type, String message){
        this.type = type.toUpperCase();
        this.message = message;
    }

    /*
    public Popup(String type, String code){

        this.type = type;
        message = ERR_MSG_EN;
        this.code = code;

        this.message = this.message+"\nCode: "+code;

    }
    */

    public Popup(String type){
        this.type = type.toUpperCase();
        message = ERR_MSG_EN;
        code = CODE;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
