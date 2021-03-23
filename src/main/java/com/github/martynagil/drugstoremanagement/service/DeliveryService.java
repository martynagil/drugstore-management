package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.DeliveryDto;
import com.github.martynagil.drugstoremanagement.model.Delivery;
import com.github.martynagil.drugstoremanagement.repositories.DeliveryRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class DeliveryService {

	private ShopRepository shopRepository;
	private DeliveryRepository deliveryRepository;

	public DeliveryService(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	@Transactional
	public void createDelivery(DeliveryDto deliveryDto) {
		Delivery delivery = createDeliveryFromDto(deliveryDto);
		deliveryRepository.save(delivery);
	}

	private Delivery createDeliveryFromDto(DeliveryDto deliveryDto) {
		return new Delivery(
				deliveryDto.getTime(),
				shopRepository.findById(deliveryDto.getShopId())
						.orElseThrow(EntityNotFoundException::new),
				deliveryDto.getDeliveryEntries()
		);
	}
}
