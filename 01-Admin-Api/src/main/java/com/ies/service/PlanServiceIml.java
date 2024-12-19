package com.ies.service;

import com.ies.bindings.PlanForm;
import com.ies.constants.AppConstants;
import com.ies.entities.PlansEntity;
import com.ies.repositories.PlanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceIml implements PlanService{


    @Autowired
    private PlanRepository planRepo;

    @Override
    public boolean createPlan(PlanForm planForm) {

        PlansEntity entity = new PlansEntity();

        BeanUtils.copyProperties(planForm, entity);
        // entity.setActiveSw(AppConstants.Y_STR);
        planRepo.save(entity);
        return true;
    }

    @Override
    public List<PlanForm> fetchPlans() {
        List<PlansEntity> planEntity = planRepo.findAll();

        List<PlanForm> plans = new ArrayList<>();
        for (PlansEntity plan : planEntity) {

            PlanForm form = new PlanForm();
            BeanUtils.copyProperties(plan, form);
            plans.add(form);
        }
        return plans;
    }

    @Override

    public PlanForm getPlanById(Integer planId) {
        Optional<PlansEntity> optional = planRepo.findById(planId);
        if (optional.isPresent()) {
            PlansEntity planEntity = optional.get();
            PlanForm plan=new PlanForm();
            BeanUtils.copyProperties(planEntity, plan);
            return plan;
        }
        return null;
    }

    @Override
    public String changePlanStatus(Integer planId, String status) {
        Integer cn = planRepo.changePStatus(planId, status);
        if (cn > 0) {
            return AppConstants.PLAN_STATUS_SUCCESS;
        } else {
            return AppConstants.PLAN_STATUS_FAILD;
        }
    }
}
