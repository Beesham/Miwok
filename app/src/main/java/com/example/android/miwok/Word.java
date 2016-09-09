package com.example.android.miwok;

/**
 * Created by Beesham on 06-09-16.
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a default translation and a Miwok translation for that word
 */
public class Word {

    private String mMiwok_translation;
    private String mDefault_translation;
    private int imageResourceId = -1;
    private int soundResourceId = -1;
    private static int NO_IMAGE_RESOURCE = -1;

    /*
         * Creates a new word object
         * @param defaultTranslation is the word the user is familiar with
         * @param miwokTranslation is the word in the Miwok language
         */
    public Word(String defaultTranslation, String miwokTranslation, int soundResourceId) {
        mDefault_translation = defaultTranslation;
        mMiwok_translation = miwokTranslation;
        this.soundResourceId = soundResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int soundResourceId) {
        mDefault_translation = defaultTranslation;
        mMiwok_translation = miwokTranslation;
        this.imageResourceId = imageResourceId;
        this.soundResourceId = soundResourceId;
    }

    public String getMiwok_translation(){
       return mMiwok_translation;
    }

    public String getDefault_translation(){
        return mDefault_translation;
    }

    public String setMiwok_word(){
        return null;
    }

    public String setEnglish_word(){
        return null;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getSoundResourceId() {
        return soundResourceId;
    }

    public boolean hasImage(){
        if(imageResourceId != NO_IMAGE_RESOURCE) return true;
        else return false;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mMiwok_translation='" + mMiwok_translation + '\'' +
                ", mDefault_translation='" + mDefault_translation + '\'' +
                ", imageResourceId=" + imageResourceId +
                ", soundResourceId=" + soundResourceId +
                '}';
    }

}
