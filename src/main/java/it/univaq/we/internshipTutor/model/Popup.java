package it.univaq.we.internshipTutor.model;

public class Popup {

    private String kind;
    private String message;
    private String code;

    // defaults
    private static final String ERROR = "ERROR";
    private static final String WARNING = "WARNING";
    private static final String SUCCESS = "SUCCESS";

    private static final String CODE = "";

    private static final String ERR_MSG_IT = "Non è stato possibile completare l'operazione";
    private static final String ERR_MSG_EN = "The operation cannot be completed";

    private static final String WAR_MSG_IT = "Qualcosa è andato storto";
    private static final String WAR_MSG_EN = "Something went wrong";

    private static final String SUCC_MSG_IT = "Operazione eseguita con successo";
    private static final String SUCC_MSG_EN = "Operation completed";


    public Popup(){
        kind = SUCCESS;
        message = SUCC_MSG_EN;
        code = CODE;
    }

    public Popup(String kind, String message, String code){
        this.kind = kind;
        this.code = code;
        this.message = message+"\nCode: "+code;

    }

    public Popup(String kind, String code){
        this.kind = kind;
        message = ERR_MSG_EN;
        this.code = code;

        this.message = this.message+"\nCode: "+code;

    }

    public Popup(String kind){
        this.kind = kind;
        message = ERR_MSG_EN;
        code = CODE;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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
