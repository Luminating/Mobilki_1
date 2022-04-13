package com.example.mobilki_1;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Profiles {
    private static Profiles instance;
    private ArrayList<Profile> profileList = new ArrayList<>();
    private static final String fileName = "profiles.txt";
    private int currentProfileIndex = 0;
    private final static String DELIMITER = "\t";

    public static synchronized Profiles getInstance(){
        if (instance == null) {
            instance = new Profiles();
        }
        return instance;
    }

    private Profiles(){

    }

    public ArrayList<Profile> getProfileList() {
        return profileList;
    }

    public void addProfile(String profileName) {

        profileList.add(new Profile(profileName, 0));
        currentProfileIndex = profileList.size() - 1;
    }

    public boolean isProfileNamePresent(String name) {
        boolean result = false;
        for (Profile profile: profileList){
            if (profile.name.equals(name)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean changeProfile(String name){
        boolean result = false;
        for (int index = 0; index < profileList.size(); index++){
            if (profileList.get(index).name.equals(name)) {
                currentProfileIndex = index;
                result = true;
                break;
            }
        }
        return result;
    }

    public void editCurrentProfile(String name){
        profileList.get(currentProfileIndex).name = name;
    }

    public boolean deleteProfile(String name){
        boolean result = false;
        Profile currentProfile = getCurrentProfile();
        Iterator<Profile> iterator = profileList.iterator();
        while (iterator.hasNext()){
            String profileName = iterator.next().name;
            if (profileName.equals(name)){
                result = true;
                iterator.remove();
                break;
            }
        }
        currentProfileIndex = profileList.indexOf(currentProfile);
        return result;
    }

    public Profile getCurrentProfile(){
        return (profileList.size() != 0) ? profileList.get(currentProfileIndex): null;
    }

    public void writeToFile(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            for (int index = 0; index < profileList.size(); index++){
                String flag = (index == currentProfileIndex) ? "1": "0";
                bufferedWriter.write(profileList.get(index).name + DELIMITER + String.valueOf(profileList.get(index).score) + DELIMITER + flag + DELIMITER + String.valueOf(profileList.get(index).lastGameDate.getTime()) + "\n");
            }
            bufferedWriter.close();
            outputStreamWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void readFromFile(Context context) {
        profileList.clear();
        currentProfileIndex = 0;
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String receiveString = "";
                int index = 0;
                while ((receiveString = bufferedReader.readLine()) != null) {
                    String[] array = receiveString.split(DELIMITER);
                    if (array.length >= 3) {
                        Profile profile = new Profile(array[0], Integer.parseInt(array[1]));
                        if (array.length > 3){
                            profile.lastGameDate = new Date(Long.parseLong(array[3]));
                        }
                        profileList.add(profile);
                        if (array[2].equals("1")) {
                            currentProfileIndex = index;
                        }
                        index++;
                    }
                }
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            //Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            //Log.e("login activity", "Can not read file: " + e.toString());
        }
        if (profileList.size() == 0) {
            profileList.add(new Profile("no name", 0));
            currentProfileIndex = 0;
        }
    }
}

