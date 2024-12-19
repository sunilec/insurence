package com.ies.service;

import java.util.ArrayList;
import java.util.List;

import com.ies.bindings.App;
import com.ies.constants.AppConstants;
import com.ies.entities.AppEntity;
import com.ies.entities.UserEntity;
import com.ies.exception.SsaWebException;
import com.ies.repositories.AppRepo;
import com.ies.repositories.UserRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ArServiceImpl implements ArService {

	@Autowired
	private AppRepo appRepo;

	@Autowired
	private UserRepo userRepo;

	private static final String SSA_WEB_API_URL = "https://ssa.web.app/{ssn}";

	@Override
	public String createApplication(App app) {
		try {
			WebClient webClient = WebClient.create();

			String stateName = webClient.get().uri(SSA_WEB_API_URL, app.getSsn()).retrieve().bodyToMono(String.class)
					.block();

			if (AppConstants.RI.equals(stateName)) {

				UserEntity userEntity = userRepo.findById(app.getUserId()).get();

				AppEntity appEntity = new AppEntity();
				BeanUtils.copyProperties(app, appEntity);

				appEntity.setUser(userEntity);

				appEntity = appRepo.save(appEntity);
				return "App Created with Case Num : " + appEntity.getCaseNum();
			}
		} catch (Exception e) {
			throw new SsaWebException(e.getMessage());
		}

		return AppConstants.INVALID_SSN;
	}

	@Override
	public List<App> fetchApps(Integer userId) {

		UserEntity userEntity = userRepo.findById(userId).get();
		Integer roleId = userEntity.getRoleId();

		List<AppEntity> appEntities = null;

		if (1 == roleId) {
			appEntities = appRepo.fetchUserApps();
		} else {
			appEntities = appRepo.fetchCwApps(userId);
		}
		
		List<App> apps = new ArrayList<>();
		
		for(AppEntity entity : appEntities) {
			App app = new App();
			BeanUtils.copyProperties(entity, apps);
			apps.add(app);
		}

		return apps;
	}

}
