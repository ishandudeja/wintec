package com.example.ishan.wintecapp;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "wintec.db";

    //We need to pass database information along to superclass
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String user = " CREATE TABLE users (" +
                "  id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  name TEXT," +
                "  email TEXT," +
                "  password TEXT," +
                "  isAdmin BOOLEAN" +
                " ) ";

        String pathway = "create table pathway (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name text," +
                "description text" +
                ")";

        String student = "create table student(" +
                "id integer primary key autoincrement," +
                "user_id integer," +
                "student_id integer," +
                "name text," +
                "pathway_id integer," +
                "semesterStart Text," +
                "semesterEnd Text," +
                "image BLOB," +
                "isActive boolean," +
                "foreign key (pathway_id) references pathway(id)," +
                "foreign key (user_id) references users(id)" +
                ")";


        String module = "create table module (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code text," +
                "title text," +
                "level integer," +
                "credit integer," +
                "description text," +
                "isCore boolean," +
                "pathway_id integer," +
                "semester integer," +
                "foreign key (pathway_id) references pathway(id)" +
                ")";

        String requisites = "create table moduleRequisites (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "module_id integer," +
                "requisite_id integer," +
                "isCoRequisite boolean," +
                "isActive boolean," +
                "foreign key (module_id) references module(id)," +
                "foreign key (requisite_id) references module(id)" +
                ")";

        String studentModule = "create table studentModule (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_id integer," +
                "module_id integer," +
                "semester Text," +
                "completionRate integer," +
                "isCompete boolean," +
                "grade text," +
                "result text," +
                "isActive boolean," +
                "foreign key (module_id) references module(id)," +
                "foreign key (student_id) references users(id)" +
                ")";


        sqLiteDatabase.execSQL(user);
        sqLiteDatabase.execSQL(pathway);
        sqLiteDatabase.execSQL(student);
        sqLiteDatabase.execSQL(module);
        sqLiteDatabase.execSQL(requisites);
        sqLiteDatabase.execSQL(studentModule);

//       dbUserInsert();
//       insertPathway();
//       insertModule();


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pathway");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS student");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS module");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS moduleRequisites");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS studentModule");
        onCreate(sqLiteDatabase);
    }


    public long createPathway(String name, String description) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("description", description);

        SQLiteDatabase db = getWritableDatabase();
        long rowid = db.insert("pathway", null, values);
        db.close();

        return rowid;

    }

    public void insertPathway() {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM pathway";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        if (recordSet.getCount() == 0) {

            createPathway("Software Engineering", "Software Engineering");
            createPathway("Database Architecture", "Database Architecture");
            createPathway("Networking", "Networking");
            createPathway("Multi Media Web Development", "Multi Media Web Development");

        }
    }

    public long createModule(Module module) {

        ContentValues values = new ContentValues();
        values.put("code", module.get_code());
        values.put("title", module.get_title());
        values.put("level", module.get_level());
        values.put("description", module.get_description());
        values.put("isCore", module.is_isCore());
        values.put("pathway_id", module.get_pathway_id());
        values.put("semester", module.get_semester());
        SQLiteDatabase db = getWritableDatabase();
        long rowid = db.insert("module", null, values);
        db.close();

        return rowid;
    }

    public long createStudentModule(StudentModule studentModule) {

        ContentValues values = new ContentValues();
        values.put("student_id", studentModule.get_student_id());
        values.put("module_id", studentModule.get_module_id());
        values.put("semester", studentModule.get_semester());
        values.put("completionRate", studentModule.get_completionRate());
        values.put("isCompete", studentModule.is_isCompete());
        values.put("grade", studentModule.get_grade());
        values.put("result", studentModule.get_result());
        values.put("isActive", studentModule.is_isActive());
        SQLiteDatabase db = getWritableDatabase();
        long rowid = db.insert("studentModule", null, values);
        db.close();

        return rowid;
    }

    public int isStudMapExist(StudentModule studentModule) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM studentModule WHERE student_id =" + studentModule.get_student_id() + " and module_id= " + studentModule.get_module_id() + "";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results

        if (recordSet.getCount() > 0) {
            recordSet.moveToFirst();
            return recordSet.getInt(recordSet.getColumnIndex("id"));

        }
        return 0;

    }


    public long updateStudentModule(StudentModule studentModule, int id) {

        ContentValues values = new ContentValues();
        values.put("student_id", studentModule.get_student_id());
        values.put("module_id", studentModule.get_module_id());
        values.put("semester", studentModule.get_semester());
        values.put("completionRate", studentModule.get_completionRate());
        values.put("isCompete", studentModule.is_isCompete());
        values.put("grade", studentModule.get_grade());
        values.put("result", studentModule.get_result());
        values.put("isActive", studentModule.is_isActive());
        SQLiteDatabase db = getWritableDatabase();
        long rowid = db.update("studentModule", values, "id=" + id + "", null);
        db.close();

        return rowid;
    }


    public void insertModule() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM module";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        if (recordSet.getCount() == 0) {
            //year one sem 1
            createModule(new Module("COMP501", "Information Technology Operations", 5, 15, "To enable students to apply fundamental IT technical support concepts and practice, and configure and administer systems and applications to meet organisational requirements.", true, null, 1));
            createModule(new Module("COMP502", "Fundamentals of Programming and Problem Solving", 5, 15, "To enable students to apply the principles of software development to create simple working applications and use problem-solving and decision-making techniques to provide innovative and timely Information Technology outcomes", true, null, 1));
            createModule(new Module("INFO501", "Professional Practice", 5, 15, "To enable students to apply professional, legal, and ethical principles and practices in a socially responsible manner as an emerging IT professional, and apply communication, personal and interpersonal skills to enhance effectiveness in an IT role", true, null, 1));
            createModule(new Module("INFO502", "Business Systems Analysis and Design", 5, 15, "The student will be able to apply the fundamentals of information systems concepts and practice to support and enhance organisational processes and systems; and apply the fundamentals of interaction design concepts and practice to enhance interface design.", true, null, 1));
            //sem 2
            createModule(new Module("COMP503", "Introduction to Networks", 5, 15, "To enable students to apply a broad operational knowledge of networking, and associated services and technologies to meet typical organisational requirements.", true, null, 2));
            createModule(new Module("COMP504", "Operating Systems and Systems Support", 5, 15, "To enable students to select, install, and configure IT hardware and systems software and use graphical (GUI) and command line interfaces (CLI) to meet organisational requirements.", true, null, 2));
            createModule(new Module("INFO503", "Database Principles", 5, 15, "To enable students to apply a broad operational knowledge of database administration to meet typical organisational data storage and retrieval requirements, and apply conceptual knowledge of cloud services and virtualisation.", true, null, 2));
            createModule(new Module("INFO504", "Technical Support", 5, 15, "To enable students to demonstrate an operational knowledge and understanding of IT service management, identify common issues related to IT security, and troubleshoot and resolve a range of common system problems.", true, null, 2));

            // year two sem 3
            createModule(new Module("COMP601", "Object Oriented Programming", 6, 15, "To enable students to gain the skills to develop software applications using Object Oriented Programming techniques. Students will enrich their programming and problem solving skills by applying classes, objects, constructors, methods and properties, inheritance and polymorphism to develop programming applications.", true, null, 3));
            createModule(new Module("INFO601", "Database Modelling and SQL", 6, 15, "To enable students to apply an indepth understanding of database modelling, database design and SQL concepts.", true, null, 3));
            createModule(new Module("MATH601", "Mathematics for Information Technology", 6, 15, "To enable students to gain mathematical skills and acquire in-depth understanding of mathematics as applied to Information Technology.", true, null, 3));
            createModule(new Module("COMP602", "Web Development", 6, 15, "To enable students to gain the in depth knowledge and skills required to be able to write programs in web programming languages that solve various web programming tasks.", true, null, 3));


            //sem 4
            createModule(new Module("INFO602", "Business, Interpersonal Communications & Technical Writing", 7, 15, "To enable students to understand and apply the theory of project management with particular emphasis on Information Technology projects.", true, null, 4));

            //SE
            createModule(new Module("COMP605", "Data Structures and Algorithms", 6, 15, "To enable students to apply programming and analytical skills to implement and analyze common data structures for computer programs. Students will cover a wide range of computer programming algorithms.", false, 1, 4));
            createModule(new Module("COMP609", "Application Development", 6, 15, "Students will gain in-depth programming and problem solving skills. They will be able to use a modern development environment and a programming language to implement a working solution. This includes rigorous programming and effective use of built-in data structures and other useful features of the development environment.", false, 1, 4));
            createModule(new Module("MATH602", "Mathematics for Programming", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 1, 4));


            //  (NE Pathway)
            createModule(new Module("COMP615", "Data Centre Infrastructure", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 3, 4));
            createModule(new Module("INFO603", "Systems Administration", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 3, 4));
            createModule(new Module("COMP604", "Routing & Switching Essentials", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 3, 4));


            //DB Pathway
            createModule(new Module("COMP605", "Data Structures & Algorithms", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 2, 4));
            createModule(new Module("COMP606", "Web Programming", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 2, 4));
            createModule(new Module("INFO604", "Database Systems", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 2, 4));

            //(WD Pathway)
            createModule(new Module("COMP603", "The Web Environment", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 4, 4));
            createModule(new Module("COMP606", "Web Programming", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 4, 4));
            createModule(new Module("COMP607", "Visual Effects and Animation", 6, 15, "To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics", false, 4, 4));


            //year three sem 5
            //SE
            createModule(new Module("INFO704", "Data-Warehousing and Business Intelligence", 7, 15, "To enable students to examine the main components of data warehousing and apply it to business intelligence applications, enabling them to provide solutions which incorporate extracting data from different sources, storing data in a data warehouse and developing applications for business decision-making.", false, 1, 5));
            createModule(new Module("COMP707", "Principles of Software Testing", 7, 15, "Students will gain comprehensive knowledge of software testing methodologies and software testing tools used in industry and apply fundamental aspects of software testing incorporating system requirements, quality assurance, testing processes, automation, testing types and testing levels. This forms the third part of the Software Engineering Capstone Project.", false, 1, 5));
            createModule(new Module("COMP709", "Mobile Applications Development", 7, 15, "To enable students to design, develop and implement mobile applications on a given platform.", false, 1, 5));
            createModule(new Module("COMP706", "Game Development", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 1, 5));

            //  (NE Pathway)
            createModule(new Module("COMP701", "Advanced Networking", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 3, 5));
            createModule(new Module("INFO702", "Cyber-Security", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 3, 5));
            createModule(new Module("COMP704", "Network Security", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 3, 5));
            createModule(new Module("COMP702", "Scaling Networks", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 3, 5));

            //DB Pathway
            createModule(new Module("INFO704", "Data-Warehousing and Business Intelligence", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 2, 5));
            createModule(new Module("INFO706", "Database Front-End Applications", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 2, 5));
            createModule(new Module("COMP709", "Mobile Applications Development", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 2, 5));
            createModule(new Module("INFO707", "Cloud Server Databases", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 2, 5));

            //(WD Pathway)
            createModule(new Module("INFO709", "Human Computer Interaction", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 4, 5));
            createModule(new Module("INFO702", "Cyber-Security", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 4, 5));
            createModule(new Module("COMP709", "Mobile Application Development", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 4, 5));
            createModule(new Module("COMP710", "Web Applications Development", 7, 15, "To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming", false, 4, 5));


            //sem 6
            createModule(new Module("INFO703", "Big Data and Analytics", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, 1, 6));
            createModule(new Module("COMP715", "Software Engineering Project", 7, 15, "Students will gain advanced software development skills. They will be able to provide an in depth analysis of prototyping, performance, correctness, software reusability, scalability and quality and maintenance and appropriate documentation. This module is the Software Engineering capstone project.", false, 1, 6));


            //comman
            createModule(new Module("INFO710", "Industry Placement/Internship", 7, 30, "This module will enable students to demonstrate that they can successfully undertake original work that applies the theoretical and practical knowledge gained in other modules in a workplace environment.Enable student to gain real world experience and build and Industry portfolio. Making professional contacts to build future industry networks", false, null, 6));
            createModule(new Module("DFNZ701", "Design Factory", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, null, 6));
            createModule(new Module("BIZM701", "Business Essentials for IT Professionals", 7, 15, "To enable students to develop an understanding of the common principles of business practice whilst focussing on the theoretical and practical concepts of accounting, marketing and management in order to understand the business context within which Information Technology solutions are developed.", true, null, 6));


            //  (NE Pathway)
            createModule(new Module("COMP705", "Connecting Networks", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, 3, 6));
            createModule(new Module("COMP714", "Network Engineering Project", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, 3, 6));

            //DB Pathway

            createModule(new Module("INFO703", "Big Data and Analytics", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, 2, 6));
            createModule(new Module("INFO712", "Database Architecture Project", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, 2, 6));


            //(WD Pathway)
            createModule(new Module("COMP713", " Web Development Project", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, 4, 6));
            createModule(new Module("INFO708", "Data Visualisation", 7, 15, "To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms", false, 4, 6));


        }
    }

    public long createUser(Users user) {
        ContentValues values = new ContentValues();
        values.put("name", user.get_name());
        values.put("email", user.get_email());
        values.put("password", user.get_password());
        values.put("isAdmin", user.is_isAdmin());
        SQLiteDatabase db = getWritableDatabase();
        long rowid = db.insert("users", null, values);
        db.close();

        return rowid;
    }

    public boolean isUserExist(String UserName) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM users WHERE email ='" + UserName + "'";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results

        return recordSet.getCount() > 0;

    }

    public Users LoginUser(String userName, String password) {
        if (isUserExist(userName)) {
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM users WHERE email ='" + userName + "'";// why not leave out the WHERE  clause?

            //Cursor points to a location in your results
            Cursor recordSet = db.rawQuery(query, null);
            recordSet.moveToFirst();
            String pwd = recordSet.getString(recordSet.getColumnIndex("password"));
            Log.i("search", "password:" + pwd);
            if (pwd.equals(password)) {
                Users u = new Users();
                u.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));
                u.set_name(recordSet.getString(recordSet.getColumnIndex("name")));
                u.set_email(recordSet.getString(recordSet.getColumnIndex("email")));
                u.set_isAdmin( recordSet.getInt(recordSet.getColumnIndex("isAdmin"))==1);
                return u;

            }
            return null;

        }

        return null;

    }

    public void dbUserInsert() {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM users";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        if (recordSet.getCount() == 0) {

            createUser(new Users("ishan", "ishdud12@wintec.ac.nz", "123456789", true));
            createUser(new Users("Blaine", "blaine@wintec.ac.nz", "WinITDMP01", true));
            createUser(new Users("Dileep", "dileep@wintec.ac.nz", "WinITDMP01", true));
            createUser(new Users("Ed", "ed@wintec.ac.nz", "WinITDMP01", true));
        }

    }

    public ArrayList<Module> getModule() {


        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from module";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        ArrayList<Module> modules = new ArrayList<>();
        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {

            Module module = new Module(
                    recordSet.getString(recordSet.getColumnIndex("code"))
                    , recordSet.getString(recordSet.getColumnIndex("title"))
                    , recordSet.getInt(recordSet.getColumnIndex("level"))
                    , recordSet.getInt(recordSet.getColumnIndex("credit"))
                    , recordSet.getString(recordSet.getColumnIndex("description"))
                    , Boolean.parseBoolean(recordSet.getString(recordSet.getColumnIndex("isCore")))
                    , recordSet.isNull(recordSet.getColumnIndex("pathway_id")) ? null : recordSet.getInt(recordSet.getColumnIndex("pathway_id"))
                    , recordSet.getInt(recordSet.getColumnIndex("semester"))


            );
            module.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));

            modules.add(module);
            recordSet.moveToNext();
        }
        db.close();

        return modules;
    }

    public ArrayList<Module> getModuleByPathway(int pathway) {


        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from module where pathway_id IS NULL or pathway_id=" + pathway + "";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        ArrayList<Module> modules = new ArrayList<>();
        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {

            Module module = new Module(
                    recordSet.getString(recordSet.getColumnIndex("code"))
                    , recordSet.getString(recordSet.getColumnIndex("title"))
                    , recordSet.getInt(recordSet.getColumnIndex("level"))
                    , recordSet.getInt(recordSet.getColumnIndex("credit"))
                    , recordSet.getString(recordSet.getColumnIndex("description"))
                    , Boolean.parseBoolean(recordSet.getString(recordSet.getColumnIndex("isCore")))
                    , recordSet.isNull(recordSet.getColumnIndex("pathway_id")) ? null : recordSet.getInt(recordSet.getColumnIndex("pathway_id"))
                    , recordSet.getInt(recordSet.getColumnIndex("semester"))


            );
            module.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));

            modules.add(module);
            recordSet.moveToNext();
        }
        db.close();

        return modules;
    }

    public long createStudent(Student student) {


        ContentValues values = new ContentValues();
        values.put("user_id", student.get_user_id());
        values.put("student_id", student.get_student_id());
        values.put("name", student.get_name());
        values.put("pathway_id", student.get_pathway_id());
        values.put("semesterStart", student.get_semesterStart());
        values.put("semesterEnd", student.get_semesterEnd());
        values.put("image", student.get_Byte_image());
        values.put("isActive", student.is_isActive());
        SQLiteDatabase db = getWritableDatabase();
        long rowid = db.insert("student", null, values);
        db.close();

        return rowid;
    }

    public ArrayList<Student> getStudents() {


        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from student";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        ArrayList<Student> students = new ArrayList<>();
        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {

            Student student = new Student(
                    recordSet.getInt(recordSet.getColumnIndex("student_id"))
                    , recordSet.getString(recordSet.getColumnIndex("name"))
                    , recordSet.getInt(recordSet.getColumnIndex("pathway_id"))
                    , recordSet.getString(recordSet.getColumnIndex("semesterStart"))
                    , recordSet.getString(recordSet.getColumnIndex("semesterEnd"))
                    , Boolean.parseBoolean(recordSet.getString(recordSet.getColumnIndex("isActive")))
                    , null
                    , recordSet.getInt(recordSet.getColumnIndex("user_id"))


            );
            student.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));
            student.set_Byte_image(recordSet.getBlob(recordSet.getColumnIndex("image")));
            students.add(student);
            recordSet.moveToNext();
        }
        db.close();

        return students;
    }

    public ArrayList<Module> getModuleByPathway_sem(int pathway, int sem) {


        SQLiteDatabase db = getWritableDatabase();
        String query = "";
        if (sem == 1 || sem == 2 || sem == 3) {
            query = "select * from module where pathway_id IS NULL  and  semester= " + sem + "  ";
        } else {
            query = "select * from module where (pathway_id IS NULL or pathway_id=" + pathway + ") and  semester=" + sem + " ";// why not leave out the WHERE  clause?
        }
        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        ArrayList<Module> modules = new ArrayList<>();
        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {

            Module module = new Module(
                    recordSet.getString(recordSet.getColumnIndex("code"))
                    , recordSet.getString(recordSet.getColumnIndex("title"))
                    , recordSet.getInt(recordSet.getColumnIndex("level"))
                    , recordSet.getInt(recordSet.getColumnIndex("credit"))
                    , recordSet.getString(recordSet.getColumnIndex("description"))
                    , recordSet.getInt(recordSet.getColumnIndex("isCore")) == 1 ? true : false
                    , recordSet.isNull(recordSet.getColumnIndex("pathway_id")) ? null : recordSet.getInt(recordSet.getColumnIndex("pathway_id"))
                    , recordSet.getInt(recordSet.getColumnIndex("semester"))


            );
            module.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));

            modules.add(module);
            recordSet.moveToNext();
        }
        db.close();

        return modules;
    }

    public ArrayList<StudentModule> getStudentModuleByStud_Id(int id) {


        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from studentModule where student_id=" + id + "";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        ArrayList<StudentModule> studentModules = new ArrayList<>();
        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {

            StudentModule module = new StudentModule(
                    recordSet.getInt(recordSet.getColumnIndex("student_id"))
                    , recordSet.getInt(recordSet.getColumnIndex("module_id"))
                    , recordSet.getString(recordSet.getColumnIndex("semester"))
                    , recordSet.getInt(recordSet.getColumnIndex("completionRate"))
                    , recordSet.getInt(recordSet.getColumnIndex("isCompete")) == 1 ? true : false
                    , recordSet.getString(recordSet.getColumnIndex("grade"))
                    , recordSet.getString(recordSet.getColumnIndex("result"))
                    , recordSet.getInt(recordSet.getColumnIndex("isActive")) == 1 ? true : false


            );
            module.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));

            studentModules.add(module);
            recordSet.moveToNext();
        }
        db.close();

        return studentModules;
    }

    public Module getModuleById(int id) {


        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from module where id=" + id + "";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        Module module=null;
        if (recordSet.getCount() > 0) {
            recordSet.moveToFirst();

            //Position after the last row means the end of the results

             module = new Module(
                    recordSet.getString(recordSet.getColumnIndex("code"))
                    , recordSet.getString(recordSet.getColumnIndex("title"))
                    , recordSet.getInt(recordSet.getColumnIndex("level"))
                    , recordSet.getInt(recordSet.getColumnIndex("credit"))
                    , recordSet.getString(recordSet.getColumnIndex("description"))
                    , recordSet.getInt(recordSet.getColumnIndex("isCore"))==1
                    , recordSet.isNull(recordSet.getColumnIndex("pathway_id")) ? null : recordSet.getInt(recordSet.getColumnIndex("pathway_id"))
                    , recordSet.getInt(recordSet.getColumnIndex("semester")));


            module.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));

        }


        db.close();

        return module;
    }

    public long updateModule(Module module) {

        ContentValues values = new ContentValues();
        values.put("code", module.get_code());
        values.put("title", module.get_title());
        values.put("level", module.get_level());
        values.put("description", module.get_description());
        values.put("isCore", module.is_isCore());
        values.put("pathway_id", module.get_pathway_id());
        values.put("semester", module.get_semester());
        SQLiteDatabase db = getWritableDatabase();
        long rowid = db.update("module", values,"id="+module.get_id()+"",null);
        db.close();

        return rowid;
    }

    public Student getStudentByUser_Id(int id) {


        SQLiteDatabase db = getWritableDatabase();
        String query = "select * from student where user_id=" + id + "";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        Student student=null;
        if (recordSet.getCount() > 0) {
            recordSet.moveToFirst();

            //Position after the last row means the end of the results

            student = new Student(
                    recordSet.getInt(recordSet.getColumnIndex("student_id"))
                    , recordSet.getString(recordSet.getColumnIndex("name"))
                    , recordSet.getInt(recordSet.getColumnIndex("pathway_id"))
                    , recordSet.getString(recordSet.getColumnIndex("semesterStart"))
                    , recordSet.getString(recordSet.getColumnIndex("semesterEnd"))
                    , Boolean.parseBoolean(recordSet.getString(recordSet.getColumnIndex("isActive")))
                    , null
                    , recordSet.getInt(recordSet.getColumnIndex("user_id")));

            student.set_Byte_image(recordSet.getBlob(recordSet.getColumnIndex("image")));
            student.set_id(recordSet.getInt(recordSet.getColumnIndex("id")));

        }


        db.close();

        return student;
    }}
