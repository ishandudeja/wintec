package com.example.ishan.wintecapp;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

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

        String pathway="create table pathway (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name text," +
                "description text" +
                ")";

        String student="create table student(" +
                "id integer primary key autoincrement," +
                "user_id integer," +
                "student_id integer," +
                "name text," +
                "pathway_id integer," +
                "semesterStart date," +
                "semesterEnd date," +
                "image BLOB," +
                "isActive boolean," +
                "foreign key (pathway_id) references pathway(id)," +
                "foreign key (user_id) references users(id)" +
                ")";



        String module="create table module (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "code text," +
                "title text," +
                "level integer," +
                "credit integer," +
                "description text," +
                "isCore boolean," +
                "pathway_id integer," +
                "foreign key (pathway_id) references pathway(id)" +
                ")";

        String  requisites="create table moduleRequisites (" +
                "id  INTEGER PRIMARY KEY AUTOINCREMENT," +
                "module_id integer," +
                "requisite_id integer," +
                "isCoRequisite boolean," +
                "isActive boolean," +
                "foreign key (module_id) references module(id)," +
                "foreign key (requisite_id) references module(id)" +
                ")";

        String studentModule="create table studentModule (" +
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



        String userInsert="INSERT INTO users VALUES ('ishan','ishdud12@student.wintec.ac.nz','WinITDMP01',0)," +
                "('Blaine','Blaine@wintec.ac.nz','WinITDMP01',1)," +
                "('Dileep','Dileep@wintec.ac.nz','WinITDMP01',1),"+
                "('Ed','Ed@wintec.ac.nz','WinITDMP01',1);";

        String pathwayInsert="INSERT INTO pathway VALUES ('Software Engineering','Software Engineering')," +
                "('Database Architecture','Database Architecture')," +
                "('Networking','Networking')," +
                "('Multi Media Web Development','Multi Media Web Development')";

        String moduleInsert="INSERT INTO module VALUES ('COMP501','Information Technology Operations',5,15,'To enable students to apply fundamental IT technical support concepts and practice, and configure and administer systems and applications to meet organisational requirements.',1,null)," +
                "('COMP502','Fundamentals of Programming and Problem Solving',5,15,'To enable students to apply the principles of software development to create simple working applications and use problem-solving and decision-making techniques to provide innovative and timely Information Technology outcomes',1,null)," +
                "('INFO501','Professional Practice',5,15,'To enable students to apply professional, legal, and ethical principles and practices in a socially responsible manner as an emerging IT professional, and apply communication, personal and interpersonal skills to enhance effectiveness in an IT role',1,null)," +
                "('INFO502','Business Systems Analysis and Design',5,15,'The student will be able to apply the fundamentals of information systems concepts and practice to support and enhance organisational processes and systems; and apply the fundamentals of interaction design concepts and practice to enhance interface design.',1,null)," +
                "('COMP503','Introduction to Networks',5,15,'To enable students to apply a broad operational knowledge of networking, and associated services and technologies to meet typical organisational requirements.',1,null)," +
                "('COMP504','Operating Systems and Systems Support',5,15,,'To enable students to select, install, and configure IT hardware and systems software and use graphical (GUI) and command line interfaces (CLI) to meet organisational requirements.',1,null)," +
                "('INFO503','Database Principles',5,15,'To enable students to apply a broad operational knowledge of database administration to meet typical organisational data storage and retrieval requirements, and apply conceptual knowledge of cloud services and virtualisation.',1,null)," +
                "('INFO504','Technical Support',5,15,'To enable students to demonstrate an operational knowledge and understanding of IT service management, identify common issues related to IT security, and troubleshoot and resolve a range of common system problems.',1,null)," +
                "('COMP601','Object Oriented Programming',6,15,'To enable students to gain the skills to develop software applications using Object Oriented Programming techniques. Students will enrich their programming and problem solving skills by applying classes, objects, constructors, methods and properties, inheritance and polymorphism to develop programming applications.',1,null)," +
                "('INFO601','Database Modelling and SQL',6,15,'To enable students to apply an indepth understanding of database modelling, database design and SQL concepts.',1,null)," +
                "('MATH601','Mathematics for Information Technology',6,15,'To enable students to gain mathematical skills and acquire in-depth understanding of mathematics as applied to Information Technology.',1,null)," +
                "('COMP602','Web Development',6,15,'To enable students to gain the in depth knowledge and skills required to be able to write programs in web programming languages that solve various web programming tasks.',1,null)," +
                "('BIZM701','Business Essentials for IT Professionals',7,15,'To enable students to develop an understanding of the common principles of business practice whilst focussing on the theoretical and practical concepts of accounting, marketing and management in order to understand the business context within which Information Technology solutions are developed.',1,null)," +
                "('INFO701','Project Management',7,15,'To enable students to understand and apply the theory of project management with particular emphasis on Information Technology projects.',1,null)," +

                "('COMP605','Data Structures and Algorithms',6,15,'To enable students to apply programming and analytical skills to implement and analyze common data structures for computer programs. Students will cover a wide range of computer programming algorithms.',1,1)," +
                "('MATH602','Mathematics for Programming',6,15,'To enable students to obtain the mathematical skills to facilitate an in-depth understanding of advanced programming techniques. Students will be able to solve problems in recurrence functions, asymptotic functions, algorithmic puzzles, combinatorics and graph theory and advanced topics in probability and statistics',1,1)," +
                "('COMP609','Application Development',6,15,'Students will gain in-depth programming and problem solving skills. They will be able to use a modern development environment and a programming language to implement a working solution. This includes rigorous programming and effective use of built-in data structures and other useful features of the development environment.',1,1)," +
                "('INFO703','Big Data and Analytics',7,15,'To enable students to gain the practical knowledge and skills required to store, manage and analyse large amounts of data, using appropriate algorithms',1,1)," +
                "('COMP707','Principles of Software Testing',7,15,'Students will gain comprehensive knowledge of software testing methodologies and software testing tools used in industry and apply fundamental aspects of software testing incorporating system requirements, quality assurance, testing processes, automation, testing types and testing levels. This forms the third part of the Software Engineering Capstone Project.',1,1)," +
                "('COMP706','Game Development',7,15,'To enable students to understand supporting theories and principles of game design and apply these to the art and science of game design, development and programming',1,1)," +
                "('COMP709','Mobile Applications Development',7,15,'To enable students to design, develop and implement mobile applications on a given platform.',1,1)," +
                "('INFO704','Data-Warehousing and Business Intelligence',7,15,'To enable students to examine the main components of data warehousing and apply it to business intelligence applications, enabling them to provide solutions which incorporate extracting data from different sources, storing data in a data warehouse and developing applications for business decision-making.',1,1)," +
                "('COMP715','Software Engineering Project',7,30,'Students will gain advanced software development skills. They will be able to provide an in depth analysis of prototyping, performance, correctness, software reusability, scalability and quality and maintenance and appropriate documentation. This module is the Software Engineering capstone project.',1,1)," +
                "('INFO710','Industry Placement/Internship',7,30,'This module will enable students to demonstrate that they can successfully undertake original work that applies the theoretical and practical knowledge gained in other modules in a workplace environment.Enable student to gain real world experience and build and Industry portfolio. Making professional contacts to build future industry networks',1,1)," ;





        sqLiteDatabase.execSQL(user);
        sqLiteDatabase.execSQL(pathway);
        sqLiteDatabase.execSQL(student);
        sqLiteDatabase.execSQL(module);
        sqLiteDatabase.execSQL(requisites);
        sqLiteDatabase.execSQL(studentModule);

        sqLiteDatabase.execSQL(userInsert);
        sqLiteDatabase.execSQL(pathwayInsert);
        sqLiteDatabase.execSQL(moduleInsert);
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
        String query = "SELECT * FROM users WHERE name ='" + UserName + "'";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results

        return recordSet.getCount() > 0;

    }

    public Users LoginUser(String userName, String password) {
        if (isUserExist(userName)) {
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM users WHERE name ='" + userName + "'";// why not leave out the WHERE  clause?

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

                return u;

            }
            return null;

        }

        return null;

    }

}
