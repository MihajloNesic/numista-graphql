package io.gitlab.mihajlonesic.numistagraphql.service;

import io.gitlab.mihajlonesic.numistagraphql.entity.Mintage;
import io.gitlab.mihajlonesic.numistagraphql.exception.EntityNotFoundException;
import io.gitlab.mihajlonesic.numistagraphql.repository.MintageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MintageService {

    private final MintageRepository mintageRepository;

    public MintageService(MintageRepository mintageRepository) {
        this.mintageRepository = mintageRepository;
    }
    public Mintage getMintageById(Long mintageId) {
        Optional<Mintage> mintageOptional = mintageRepository.findById(mintageId);
        if (mintageOptional.isEmpty()) {
            throw new EntityNotFoundException(1001, "Mintage with id " + mintageId + " was not found");
        }
        return mintageOptional.get();
    }

    public List<Mintage> getAllMintageByCoin(Long coinId) {
        return mintageRepository.findAllMintageByCoin(coinId);
    }

}
