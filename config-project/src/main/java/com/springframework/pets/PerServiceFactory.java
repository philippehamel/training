package com.springframework.pets;

public class PerServiceFactory {
    public PetService getPetService(String petType) {
        switch (petType){
            case "dog":
                return new DogPetService();
            case "cat":
                return new CatPetService();
            default:
                throw new RuntimeException("Pet type not supported");
        }
    }
}
