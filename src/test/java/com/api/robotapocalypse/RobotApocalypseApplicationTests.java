package com.api.robotapocalypse;

import com.api.robotapocalypse.DTO.SaveSurvivalDTO;
import com.api.robotapocalypse.DTO.UpdateSurvivalDTO;
import com.api.robotapocalypse.entity.Inventory;
import com.api.robotapocalypse.entity.Location;
import com.api.robotapocalypse.entity.Survival;
import com.api.robotapocalypse.repo.InventoryRepository;
import com.api.robotapocalypse.repo.LocationRepository;
import com.api.robotapocalypse.repo.SurvivalRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Arrays;
import java.util.List;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
class RobotApocalypseApplicationTests {

    public static String api = "/api/v1/robot-apocalypse";

    @Test
    void contextLoads() {
    }

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private SurvivalRepository survivalRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ObjectMapper objectMapper;

    List<Survival> survivals = null;
    Survival survival = new Survival();
    Location testLocate = new Location(1l, "20.13", "40.12",survival);
    SaveSurvivalDTO saveSurvivalDTO = null;
    UpdateSurvivalDTO updateSurvivalDTO = null;


    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();

        survivals= survivalRepository.findAll();
        survival = survivals.get(0);
        survival.setInventories(Arrays.asList(new Inventory(1L, "COMPUTER",survival )));
        saveSurvivalDTO =  new SaveSurvivalDTO(survival, testLocate, survival.getInventories());
        updateSurvivalDTO = new UpdateSurvivalDTO(survival.getId(), "30.449","11.112");


    }



    @Test
    public void testCreateSurvivor() throws Exception {
        String survivalDTO =new ObjectMapper().writeValueAsString(saveSurvivalDTO);
        mockMvc.perform(
                post(api+"/save-survivor")
                        .content(survivalDTO)
                        .contentType("application/json")).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.isInfected").value(false))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void testUpdateSurvivorLocation() throws Exception {
        String updateSurvival =new ObjectMapper().writeValueAsString(updateSurvivalDTO);
        mockMvc.perform(
                        post(api+"/update-survivor-location")
                                .content(updateSurvival)
                                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.latitude").value(updateSurvivalDTO.getLatitude()))
                .andExpect(jsonPath("$.survival").isNotEmpty())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void testIndicateInfectedSurvivor() throws Exception {
        mockMvc.perform(
                        get(api+"/flag-infected-survivor/{survivorId}", survival.getId())
                        .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1L))
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }


    @Test
    public void testGetPercentageOfInfectedSurvivor() throws Exception {
        mockMvc.perform(
                        get(api+"/percentage-of-infected-survivor")
                                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.PercentageOfInfectedSurvivor").isNumber())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void testGetPercentageOfNonInfectedSurvivor() throws Exception {
        mockMvc.perform(
                        get(api+"/percentage-of-non-infected-survivor")
                                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.PercentageOfNonInfectedSurvivor").isNumber())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void testGetListOfInfectedSurvivor() throws Exception {
        mockMvc.perform(
                        get(api+"/list-of-infected-survivor")
                                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }

    @Test
    public void testGetListOfNonInfectedSurvivor() throws Exception {
        mockMvc.perform(
                        get(api+"/list-of-non-infected-survivor")
                                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }



    @Test
    public void testGetAllRobotCPU() throws Exception {
        mockMvc.perform(
                        get(api)
                                .contentType("application/json")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())

                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())));
    }
}
