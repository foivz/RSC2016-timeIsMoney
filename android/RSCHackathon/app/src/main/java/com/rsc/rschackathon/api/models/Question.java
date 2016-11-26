package com.rsc.rschackathon.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {

    int id;
    int quiz_id;
    String text;
    String image;
    int type_id;

    List<Answers> answers;

    public Question(String text) {
        this.text = text;
    }

    public Question(int id, int quiz_id, String text, String image, int type_id, List<Answers> answers) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.text = text;
        this.image = image;
        this.type_id = type_id;
        this.answers = answers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public List<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answers> answers) {
        this.answers = answers;
    }

    public class Answers{
        int id;
        int question_id;
        String payload;
        int correct;


        public Answers(int id, int question_id, String payload, int correct) {
            this.id = id;
            this.question_id = question_id;
            this.payload = payload;
            this.correct = correct;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(int question_id) {
            this.question_id = question_id;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public int getCorrect() {
            return correct;
        }

        public void setCorrect(int correct) {
            this.correct = correct;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.quiz_id);
        dest.writeString(this.text);
        dest.writeString(this.image);
        dest.writeInt(this.type_id);
        dest.writeList(this.answers);
    }

    protected Question(Parcel in) {
        this.id = in.readInt();
        this.quiz_id = in.readInt();
        this.text = in.readString();
        this.image = in.readString();
        this.type_id = in.readInt();
        this.answers = new ArrayList<Answers>();
        in.readList(this.answers, Answers.class.getClassLoader());
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
