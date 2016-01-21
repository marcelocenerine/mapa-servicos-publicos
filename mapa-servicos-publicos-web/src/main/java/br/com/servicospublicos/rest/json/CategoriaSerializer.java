package br.com.servicospublicos.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.servicospublicos.domain.Categoria;

public class CategoriaSerializer extends JsonSerializer<Categoria> {

	@Override
	public void serialize(Categoria value, JsonGenerator jgen, SerializerProvider provider) throws IOException,	JsonProcessingException {
		jgen.writeStartObject();
		jgen.writeFieldName("tipo");
		jgen.writeString(value.name());
		jgen.writeFieldName("descricao");
		jgen.writeString(value.getDescricao());
		jgen.writeEndObject();
	}
}
