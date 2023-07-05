package com.devsuperior.apigithub.tests;

import com.devsuperior.apigithub.dto.GitHubUserDTO;

import java.util.ArrayList;
import java.util.List;

public class Factory {

   public static List<GitHubUserDTO> createMockUserList() {
        List<GitHubUserDTO> userList = new ArrayList<>();

        // Criar alguns objetos GitHubUserDTO de exemplo
        GitHubUserDTO user1 = new GitHubUserDTO();
        user1.setId(1);
        user1.setLogin("user1");
        // definir outros atributos...

        GitHubUserDTO user2 = new GitHubUserDTO();
        user2.setId(2);
        user2.setLogin("user2");
        // definir outros atributos...

        // Adicionar os objetos à lista
        userList.add(user1);
        userList.add(user2);

        return userList;
    }

}
