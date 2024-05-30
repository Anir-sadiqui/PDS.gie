package org.giefront.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.giefront.DTO.Contact;
import org.giefront.DTO.Entreprise;
import org.giefront.DTO.Personne;

import java.io.IOException;

public class ContactDeserializer extends JsonDeserializer<Contact> {

    @Override
    public Contact deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);

        if (node.has("nom") && node.has("prenom")) {
            return p.getCodec().treeToValue(node, Personne.class);
        } else if (node.has("formeJuridique") && node.has("raisonSocial")) {
            return p.getCodec().treeToValue(node, Entreprise.class);
        } else {
            throw new RuntimeException("Unknown contact type");
        }
    }
}
