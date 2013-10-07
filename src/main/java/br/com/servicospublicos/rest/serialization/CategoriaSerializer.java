package br.com.servicospublicos.rest.serialization;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import br.com.servicospublicos.model.Categoria;

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
