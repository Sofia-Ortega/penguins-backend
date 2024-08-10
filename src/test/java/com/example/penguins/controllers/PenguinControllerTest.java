package com.example.penguins.controllers;

import com.example.penguins.repositories.PenguinRepository;
import com.example.penguins.entities.Penguin;
import com.example.penguins.enums.Species;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PenguinController.class)
public class PenguinControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PenguinRepository penguinRepository;


    public static String asJsonString(final Object obj) {
        try {
            // ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter(); <-- to make json output prettier
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createPenguin() throws Exception {
        Penguin createdPenguin = new Penguin("joe", 0, 0, Species.ROCKHOPPER);
        createdPenguin.setId(1);

        when(penguinRepository.save(Mockito.any(Penguin.class))).thenReturn(createdPenguin);

        // reset id before send with post request
        createdPenguin.setId(null);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(createdPenguin);

        System.out.println("JSON: " + asJsonString(createdPenguin));

        mvc.perform(MockMvcRequestBuilders.post("/penguins").contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void getPenguinsTest() throws Exception {

        Penguin joe = new Penguin(1, "joe", 0, 0, Species.ROCKHOPPER);
        Penguin joey = new Penguin(2, "joey", 1, -10, Species.EMPEROR);

        Iterable<Penguin> penguins = Arrays.asList(joe, joey);
        when(penguinRepository.findAll()).thenReturn(penguins);

        mvc.perform(MockMvcRequestBuilders.get("/penguins").accept(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(asJsonString(penguins))
                );
    }
}
