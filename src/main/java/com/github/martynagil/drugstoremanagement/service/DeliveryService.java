package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.DeliveryDto;
import com.github.martynagil.drugstoremanagement.model.Delivery;
import com.github.martynagil.drugstoremanagement.model.DeliveryEntry;
import com.github.martynagil.drugstoremanagement.model.DeliveryEntryId;
import com.github.martynagil.drugstoremanagement.repositories.DeliveryRepository;
import com.github.martynagil.drugstoremanagement.repositories.ProductRepository;
import com.github.martynagil.drugstoremanagement.repositories.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class DeliveryService {

	private ShopRepository shopRepository;
	private DeliveryRepository deliveryRepository;
	private ProductRepository productRepository;

	public DeliveryService(ShopRepository shopRepository, DeliveryRepository deliveryRepository, ProductRepository productRepository) {
		this.shopRepository = shopRepository;
		this.deliveryRepository = deliveryRepository;
		this.productRepository = productRepository;
	}

	@Transactional
	public void createDelivery(DeliveryDto deliveryDto) {
		Delivery delivery = createDeliveryFromDto(deliveryDto);
		deliveryRepository.save(delivery);
	}

	private Delivery createDeliveryFromDto(DeliveryDto deliveryDto) {
		Delivery delivery = new Delivery(
				deliveryDto.getTime(),
				shopRepository.findById(deliveryDto.getShopId())
						.orElseThrow(EntityNotFoundException::new)
		);

		deliveryDto.getDeliveryEntryDtos().stream()
				.map(entry -> new DeliveryEntry(
								new DeliveryEntryId(
										productRepository.findById(entry.getProductId())
												.orElseThrow(EntityNotFoundException::new),
										delivery
								),
								entry.getCount()
						)).forEach(delivery::addEntry);

		return delivery;
	}
}
