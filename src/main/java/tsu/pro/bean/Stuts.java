package tsu.pro.bean;

public class Stuts {
    private String stuts;
    private String message;

    public String getStuts() {
        return stuts;
    }

    public void setStuts(String stuts) {
        this.stuts = stuts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Stuts returnErrorStu(String errMsg) {
        Stuts stuts = new Stuts();
        stuts.setStuts("error");
        stuts.setMessage(errMsg);
        return stuts;

    }

}
