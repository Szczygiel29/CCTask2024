package com.codecool.cctask2024.controller;

import com.codecool.cctask2024.model.entity.Player;
import com.codecool.cctask2024.model.repository.PlayerRepository;
import com.codecool.cctask2024.service.player.PlayerService;
import com.codecool.cctask2024.utils.Utils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains integration test for PlayerController
 */
@SpringBootTest
@AutoConfigureMockMvc
class PlayerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        playerRepository.saveAll(List.of(
                Player.builder()
                        .firstName("firstname_test1")
                        .lastName("lastname_test1")
                        .degree("degree_test1")
                        .build(),
                Player.builder()
                        .firstName("firstname_test2")
                        .lastName("lastname_test2")
                        .degree("degree_test2")
                        .build()));
    }

    @AfterEach
    void cleanUpEach() {
        playerRepository.deleteAll();
    }

    /**
     * Test checking an existing list of players.
     */
    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void should_return_all_players() {
        //given
        //when
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/player/all"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("firstname_test1")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName", Matchers.is("lastname_test1")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$[0].degree", Matchers.is("degree_test1")))
                    .andReturn();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //then
        assertThat(playerService.findAllPlayers()).hasSize(2);
    }

    /**
     * Test checking a saving new player.
     */
    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void should_save_player() {
        //given
        Player player = Player.builder().id(11111L).firstName("name").lastName("lastName").dateOfBirth(new Date(19999922233L)).textDate("textdate").degree("degree").sex("sex").build();
        String paymentAsString = Utils.toJsonString(player);
        List<Player> paymentListBeforeAddPayment = new ArrayList<>(playerService.findAllPlayers());
        //when
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/player/save")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(paymentAsString))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //then
        List<Player> paymentListAfterAddPayment = new ArrayList<>(playerService.findAllPlayers());
        assertThat(paymentListBeforeAddPayment).hasSize(2);
        assertThat(paymentListAfterAddPayment).hasSize(3);
    }

    @Test
    @WithMockUser(username = "testuser", roles = "ADMIN")
    void should_throw_bad_request_when_add_new_player_without_correct_bodyrequest() {
        //given
        String badRequestBody = "bad_request";
        List<Player> paymentListBeforeAddPayment = new ArrayList<>(playerService.findAllPlayers());
        //when
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/player/save")
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
                            .content(badRequestBody))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andReturn();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //then
        List<Player> paymentListAfterAddPayment = new ArrayList<>(playerService.findAllPlayers());
        assertThat(paymentListBeforeAddPayment).hasSize(2);
        assertThat(paymentListAfterAddPayment).hasSize(2);
    }
}