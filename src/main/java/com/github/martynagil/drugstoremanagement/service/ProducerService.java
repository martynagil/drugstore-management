package com.github.martynagil.drugstoremanagement.service;

import com.github.martynagil.drugstoremanagement.dto.ProducerDto;
import com.github.martynagil.drugstoremanagement.exceptions.ProducerAlreadyExistsException;
import com.github.martynagil.drugstoremanagement.model.Producer;
import com.github.martynagil.drugstoremanagement.repositories.ProducerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProducerService {

    private ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Transactional
    public void addProducer(ProducerDto producerDto) {
        if (producerExists(producerDto)) {
            throw new ProducerAlreadyExistsException();
        }

        Producer producer = createProducerFromDto(producerDto);
    }

    private Producer createProducerFromDto(ProducerDto producerDto) {
        return new Producer(
                producerDto.getName(),
                producerDto.getEmail(),
                producerDto.getTelephone()
        );
    }

    private boolean producerExists(ProducerDto producerDto) {
        return producerRepository.existsByName(producerDto.getName());
    }
}
