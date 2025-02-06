using Newtonsoft.Json.Linq;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MinecraftModGenerator.JSON_Classes.JSON_Helpers
{
    public class SingleElementArrayConverter : JsonConverter<Dictionary<string, string[]>>
    {
        public override void WriteJson(JsonWriter writer, Dictionary<string, string[]> value, JsonSerializer serializer)
        {
            JObject obj = new JObject();
            foreach (var kvp in value) {
                if (kvp.Value.Length == 1)
                    obj[kvp.Key] = kvp.Value[0];
                else
                    obj[kvp.Key] = JArray.FromObject(kvp.Value, serializer);
            }

            obj.WriteTo(writer);
        }

        public override Dictionary<string, string[]> ReadJson(JsonReader reader, Type objectType, Dictionary<string, string[]> existingValue, bool hasExistingValue, JsonSerializer serializer)
        {
            var result = new Dictionary<string, string[]>();
            JObject obj = JObject.Load(reader);

            foreach (var property in obj.Properties()) {
                if (property.Value.Type == JTokenType.String)
                    result[property.Name] = new[] { property.Value.ToString() };

                else if (property.Value.Type == JTokenType.Array)
                    result[property.Name] = property.Value.ToObject<string[]>();
            }

            return result;
        }
    }
}
