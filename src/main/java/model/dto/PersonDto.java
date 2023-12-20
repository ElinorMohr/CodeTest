package model.dto;

public class PersonDto {
    private final String name;
    private final String nationality;


    public PersonDto(String name, String nationality){
        this.name = name;
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public String getName() {
        return name;
    }
}
