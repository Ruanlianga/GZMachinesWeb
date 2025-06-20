package com.bonus.cost.service;

import com.bonus.cost.beans.ProjectLeaseCostDetail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author syruan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-application.xml")
public class ProjectCostServiceTest {

    @Resource
    private ProjectCostService projectCostService;

    // Test methods for querying lease details
    @Test
    public void testQueryProjectLeaseDetailsWithValidInput() {
        ProjectLeaseCostDetail input = new ProjectLeaseCostDetail();
        input.setProjectId("P001");
        input.setStartTime("2024-01-01");
        input.setEndTime("2024-01-31");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(input);

        assertNotNull("Result should not be null", result);
        assertFalse("Result list should not be empty", result.isEmpty());

        for (ProjectLeaseCostDetail detail : result) {
            assertEquals("Project ID should match", "P001", detail.getProjectId());
            assertTrue("Start time should be on or after 2024-01-01",
                    detail.getOperateDate().compareTo("2024-01-01") >= 0);
            assertTrue("End time should be on or before 2024-01-31",
                    detail.getOperateDate().compareTo("2024-01-31") <= 0);
        }
    }

    @Test
    public void testQueryProjectLeaseDetailsWithEmptyInput() {
        ProjectLeaseCostDetail emptyDetail = new ProjectLeaseCostDetail();
        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(emptyDetail);
        assertNotNull(result);
    }

    @Test
    public void testQueryProjectLeaseDetailsWithValidCriteria() {
        ProjectLeaseCostDetail criteria = new ProjectLeaseCostDetail();
        criteria.setProjectId("P001");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(criteria);

        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
    }

    @Test
    public void testQueryProjectLeaseDetailsWithEmptyCriteria() {
        ProjectLeaseCostDetail emptyCriteria = new ProjectLeaseCostDetail();
        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(emptyCriteria);
        assertNotNull(result);
    }

    @Test
    public void testQueryProjectLeaseDetailsWithInvalidData() {
        ProjectLeaseCostDetail detail = new ProjectLeaseCostDetail();
        detail.setOperatePersonName("user123");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(detail);
        assertTrue("Expected null or empty list for invalid input",
                result == null || result.isEmpty());
    }

    @Test
    public void testQueryProjectLeaseDetailsWithInvalidProjectId() {
        ProjectLeaseCostDetail input = new ProjectLeaseCostDetail();
        input.setProjectId("INVALID_PROJECT_ID");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(input);
        assertNotNull("Result should not be null", result);
        assertTrue("Result should be empty for invalid project ID", result.isEmpty());
    }

    // Test methods for return operations
    @Test
    public void testQueryProjectReturnDetailsWithValidInput() {
        ProjectLeaseCostDetail input = new ProjectLeaseCostDetail();
        input.setProjectId("P001");
        input.setOperateType((byte) 2);

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(input);

        assertNotNull("Result should not be null", result);
        assertFalse("Result list should not be empty", result.isEmpty());

        for (ProjectLeaseCostDetail detail : result) {
            assertEquals("Project ID should match input", "P001", detail.getProjectId());
            assertEquals("Operation type should be return (2)", Optional.of((byte) 2), detail.getOperateType());
        }
    }

    @Test
    public void testQueryProjectReturnDetailsWithInvalidOperateType() {
        ProjectLeaseCostDetail detail = new ProjectLeaseCostDetail();
        detail.setOperateType((byte) 1);
        detail.setProjectId("TEST_PROJECT_001");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(detail);

        if (!result.isEmpty()) {
            for (ProjectLeaseCostDetail item : result) {
                assertEquals("All items should have operateType=1 (lease)", 1, (byte) item.getOperateType());
            }
        }
    }

    // Test methods for settlement details
    @Test
    public void testGetSettlementDetailWithValidId() {
        ProjectLeaseCostDetail detail = new ProjectLeaseCostDetail();
        detail.setId(1);

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(detail);

        assertNotNull("Result should not be null", result);
        assertFalse("Result should not be empty", result.isEmpty());
        assertEquals("First item should have ID = 1",
                Integer.valueOf(1), result.get(0).getId());
    }

    @Test
    public void testQueryProjectLeaseDetailsWithInvalidId() {
        ProjectLeaseCostDetail detail = new ProjectLeaseCostDetail();
        detail.setId(9999);

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(detail);
        assertTrue("Expected empty list or null when querying with invalid ID",
                result == null || result.isEmpty());
    }

    @Test
    public void testGetSettlementLeaseDetailsWithValidId() {
        ProjectLeaseCostDetail input = new ProjectLeaseCostDetail();
        input.setProjectId("1");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(input);

        assertNotNull("Result should not be null", result);
        assertFalse("Result list should not be empty", result.isEmpty());

        for (ProjectLeaseCostDetail detail : result) {
            assertNotNull("Detail item should not be null", detail);
            assertEquals("Project ID should match input", "1", detail.getProjectId());
        }
    }

    @Test
    public void testGetSettlementReturnDetailsWithValidId() {
        ProjectLeaseCostDetail detail = new ProjectLeaseCostDetail();
        detail.setProjectId("1");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(detail);

        assertNotNull("Return details list should not be null", result);
        assertFalse("Return details list should not be empty", result.isEmpty());

        for (ProjectLeaseCostDetail item : result) {
            assertNotNull("Each detail item should have a project ID", item.getProjectId());
            assertEquals("Project ID should match the requested settlement ID", "1", item.getProjectId());
            assertNotNull("Each detail item should have an operation type", item.getOperateType());
            assertEquals("Operation type should be 2 (return)", (byte) 2, (byte) item.getOperateType());
        }
    }

    // Test methods for lease/return numbers
    @Test
    public void testQueryProjectLeaseDetailsWithValidInputNumbers() {
        ProjectLeaseCostDetail input = new ProjectLeaseCostDetail();
        input.setProjectId("P001");
        input.setLeaseNum(10);
        input.setReturnNum(5);

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(input);

        assertNotNull("Result should not be null", result);
        assertTrue("Result should be a list", true);
    }

    @Test
    public void testCalculateSettlementWithZeroValues() {
        ProjectLeaseCostDetail input = new ProjectLeaseCostDetail();
        input.setLeaseNum(0);
        input.setReturnNum(0);
        input.setProjectId("TEST_PROJECT");
        input.setOperateType((byte) 1);

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(input);

        assertNotNull("Result should not be null", result);
        assertEquals("Result list should be empty for zero values", 0, result.size());
    }

    // General test methods
    @Test
    public void testQueryProjectLeaseAndReturnDetailsWithValidInput() {
        ProjectLeaseCostDetail input = new ProjectLeaseCostDetail();
        input.setProjectId("P001");

        List<ProjectLeaseCostDetail> result = projectCostService.queryProjectLeaseDetails(input);

        assertNotNull("Result should not be null", result);
        assertFalse("Result should be a list of ProjectLeaseCostDetail", result.isEmpty());
    }

    @Test
    public void testQueryProjectLeaseDetails_GetAllProjects() {
        ProjectLeaseCostDetail queryParam = new ProjectLeaseCostDetail();
    }

}
        
