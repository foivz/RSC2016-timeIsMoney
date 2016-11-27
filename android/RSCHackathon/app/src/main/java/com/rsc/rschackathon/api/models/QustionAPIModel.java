package com.rsc.rschackathon.api.models;

import java.util.List;

public class QustionAPIModel {

    boolean success;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    Data data;

    public class Data {

        int id;

        String quiz_id;

        String text;

        String image;

        String type_id;

        List<Answers> answers;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getQuiz_id() {
            return quiz_id;
        }

        public void setQuiz_id(String quiz_id) {
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

        public String getType_id() {
            return type_id;
        }

        public void setType_id(String type_id) {
            this.type_id = type_id;
        }

        public List<Answers> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answers> answers) {
            this.answers = answers;
        }

        public class Answers {

            int id;

            String payload;

            String question_id;

            public String getQuestion_id() {
                return question_id;
            }

            public void setQuestion_id(String question_id) {
                this.question_id = question_id;
            }

            String correct;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPayload() {
                return payload;
            }

            public void setPayload(String payload) {
                this.payload = payload;
            }

            public String getCorrect() {
                return correct;
            }

            public void setCorrect(String correct) {
                this.correct = correct;
            }
        }
    }

    List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
