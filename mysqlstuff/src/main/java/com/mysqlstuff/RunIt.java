package com.mysqlstuff;

public class RunIt {

    public static void main(String[] args) throws Exception {
        App dao = new App();
        dao.readDataBase();
        
        DogsAndCats dac = new DogsAndCats();
        dac.readDataBasePets();
    }

}
