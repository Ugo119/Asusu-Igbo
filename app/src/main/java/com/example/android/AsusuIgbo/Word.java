package com.example.android.AsusuIgbo;

public class Word {
    //Default translation for the word
    private String mDefaultTranslation;

    //Igbo Translation for the word
    private String mIgboTranslation;
    private int mAudioResourceId;

    private int mImageResourceId = No_IMAGE_PROVIDED;

    private static final int No_IMAGE_PROVIDED = -1;

    /*
     * Create a new Word object.
     *
     * @param iWord is the name of the Igbo word
     * @param eWord is the corresponding default/English translation

     * */
    public Word(String eWord, String iWord, int audioResourceId)
    {
        mIgboTranslation = iWord;
        mDefaultTranslation = eWord;
        mAudioResourceId = audioResourceId;
    }
    /*
    Constructor to creat new Word object
    iWord is for the igbo word
    eWord is for the english word
    imageResourceId is for the drawable resource ID for the associated image
    audioResourceId is resource Id for the associated audio file for the word
     */
    public Word(String eWord, String iWord, int imageResourceId, int audioResourceId)
    {
        mIgboTranslation = iWord;
        mDefaultTranslation = eWord;
        mImageResourceId = imageResourceId;
        mAudioResourceId = audioResourceId;
    }

    /**
     * Get the Igbo word
     */
    public String getIgboWord() {
        return mIgboTranslation;
    }

    /**
     * Get the default translation
     */
    public String getEnglishWord() {
        return mDefaultTranslation;
    }

   //Get the image for the corresponding word
    public int getImageResourceId() {
        return mImageResourceId;
    }

   //returns whether or not there is an image for this word
    public boolean hasImage(){
      return mImageResourceId != No_IMAGE_PROVIDED;

    }
    //Return audio resource id
    public int getAudioResourceId(){return mAudioResourceId;}

    }