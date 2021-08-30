package com.example.miwok;

import android.content.Context;

public class Word {
    private String mMiwokTranslation;
    private String mEnglishTranslation;
    private int mAudioResourceID;
    private int mImageResourceID = -1;

    public Word(String miwokWord, String englishWord ,int audio) {
        this.mMiwokTranslation = miwokWord;
        this.mEnglishTranslation = englishWord;
        this.mAudioResourceID = audio;
        this.mAudioResourceID = audio;
    }

    public Word(String miwokWord, String englishWord, int image , int audio) {
        this.mMiwokTranslation = miwokWord;
        this.mEnglishTranslation = englishWord;
        this.mImageResourceID = image;
        this.mAudioResourceID = audio;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmEnglishTranslation() {
        return mEnglishTranslation;
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public int getmAudioResourceID(){ return mAudioResourceID;}

    public boolean hasImage() {
        return mImageResourceID != -1;
    }
}
