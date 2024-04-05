const { GoogleGenerativeAI } = require("@google/generative-ai");
const { log } = require("console");
const fs = require("fs");
require("dotenv").config();

const genAI = new GoogleGenerativeAI(process.env.GEMINI_API);


/**
 * GenerativeModel {
  apiKey: '',
  model: 'models/gemini-pro-vision',
  generationConfig: {},
  safetySettings: [],
  tools: undefined,
  requestOptions: {}
}
 */
const model = genAI.getGenerativeModel({ model: "gemini-pro-vision" });

function fileToGenerativePart(path, mimeType) {
  return {
    inlineData: {
      data: Buffer.from(fs.readFileSync(path)).toString("base64"),
      mimeType
    },
  };

}

async function run() {
  const imageParts = [
    // fileToGenerativePart("image1.png", "image/png"),
    fileToGenerativePart("./image.jpeg", "image/jpeg"),
  ];
  const prompt = "Identify the place from image, just give me the name of the place.";


  const res = await model.generateContent([prompt, ...imageParts])
  const result = await res.response


  log(result.text())
}

run()
