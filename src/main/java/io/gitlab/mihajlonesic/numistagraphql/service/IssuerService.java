package io.gitlab.mihajlonesic.numistagraphql.service;

import io.gitlab.mihajlonesic.numistagraphql.entity.Issuer;
import io.gitlab.mihajlonesic.numistagraphql.exception.EntityNotFoundException;
import io.gitlab.mihajlonesic.numistagraphql.repository.IssuerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssuerService {

    private final IssuerRepository issuerRepository;

    public IssuerService(IssuerRepository issuerRepository) {
        this.issuerRepository = issuerRepository;
    }

    public List<Issuer> getAllIssuers() {
        return issuerRepository.findAllIssuers();
    }

    public Issuer getIssuerById(Long issuerId) {
        Optional<Issuer> issuerOptional = issuerRepository.findById(issuerId);
        if (issuerOptional.isEmpty()) {
            throw new EntityNotFoundException(1001, "Issuer with id " + issuerId + " was not found");
        }
        return issuerOptional.get();
    }

    public Issuer createIssuer(String name) {
        Issuer issuer = Issuer.create(name);
        return issuerRepository.save(issuer);
    }

    public Issuer updateIssuer(Long id, String name) {
        Issuer issuer = getIssuerById(id);
        issuer.update(name);
        return issuerRepository.save(issuer);
    }

    public Boolean deleteIssuer(Long id) {
        try {
            Issuer issuer = getIssuerById(id);
            issuerRepository.delete(issuer);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
