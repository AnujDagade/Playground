import numpy as np
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import SimpleRNN, Dense, Embedding
from tensorflow.keras.optimizers import Adam

# Sample text data (can be any text corpus)
text = "hello world, this is a simple RNN example for text generation"
chars = sorted(set(text))  # All unique characters
char_to_idx = {char: idx for idx, char in enumerate(chars)}  # Mapping from char to index
idx_to_char = {idx: char for idx, char in enumerate(chars)}  # Mapping from index to char

# Hyperparameters
seq_length = 10  # Number of characters in each sequence
vocab_size = len(chars)  # Size of vocabulary
embedding_dim = 64  # Embedding dimensions
rnn_units = 128  # Number of RNN units
batch_size = 32

# Prepare training data
def create_sequences(text, seq_length):
    X = []
    y = []
    for i in range(len(text) - seq_length):
        X.append([char_to_idx[char] for char in text[i:i + seq_length]])
        y.append(char_to_idx[text[i + seq_length]])
    return np.array(X), np.array(y)

X, y = create_sequences(text, seq_length)

# Create the RNN model
model = Sequential([
    Embedding(input_dim=vocab_size, output_dim=embedding_dim, input_length=seq_length),
    SimpleRNN(rnn_units, activation='relu'),  # ReLU activation function
    Dense(vocab_size, activation='softmax')  # Softmax for output layer
])

# Compile the model
model.compile(optimizer=Adam(learning_rate=0.001), loss='sparse_categorical_crossentropy')

# Train the model
model.fit(X, y, batch_size=batch_size, epochs=100)

# Predicting the next characters using the trained model
def generate_text(model, start_string, gen_length=100):
    input_eval = [char_to_idx[char] for char in start_string]
    input_eval = np.expand_dims(input_eval, 0)  # Reshape to match the input shape of the model
    generated_text = []

    for i in range(gen_length):
        predictions = model(input_eval)
        predicted_id = tf.random.categorical(predictions[0], num_samples=1)[-1, 0].numpy()
        generated_text.append(idx_to_char[predicted_id])

        # Use the predicted character as the next input
        input_eval = np.append(input_eval[:, 1:], np.expand_dims([predicted_id], 0), axis=1)

    return start_string + ''.join(generated_text)

# Example: Generating text starting with "hello"
print(generate_text(model, start_string="hello", gen_length=100))