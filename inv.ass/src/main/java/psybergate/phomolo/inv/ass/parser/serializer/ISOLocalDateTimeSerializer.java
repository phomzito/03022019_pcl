package psybergate.phomolo.inv.ass.parser.serializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class ISOLocalDateTimeSerializer extends JsonDeserializer<LocalDateTime> {

	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
	// DateTimeFormatter.ofPattern("2015-06-28T10:13:14.743");

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		TextNode node = (TextNode) oc.readTree(jp);
		String dateString = node.textValue();

		return LocalDateTime.parse(dateString, formatter);
	}

}
