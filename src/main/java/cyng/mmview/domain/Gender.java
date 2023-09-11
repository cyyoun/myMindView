package cyng.mmview.domain;

public enum Gender {
    MAN("남자"), WOMAN("여자"), NONE("선택안함");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String gender() {
        return gender;
    }
}
