package com.java.api.modules.service.servicesql;

public class DynamicSql {
    private final String CREATE = "INSERT INTO students(`fullnamestudent` , `emailstudent` , `agestudent` , `city` , `salary` , `datetime`) " +
            "VALUES( ? , ? , ? , ? , ? , ?) ;";
    private final String READ = "SELECT * FROM students ;";
    private final String READBYID = "SELECT * FROM students WHERE orderstudent = ? ;";
    private final String UPDATE = "UPDATE students SET `fullnamestudent` = ? , `emailstudent` = ? , `agestudent` = ? ," +
            " `city` = ? , `salary` = ? ,  `datetime` = ?  WHERE orderstudent = ? ;";
    private final String DELETE = "DELETE FROM students WHERE orderstudent = ? ;";
    public String getCREATE() {
        return CREATE;
    }

    public String getREAD() {
        return READ;
    }

    public String getREADBYID() {
        return READBYID;
    }

    public String getUPDATE() {
        return UPDATE;
    }

    public String getDELETE() {
        return DELETE;
    }
}
