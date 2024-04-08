from sklearn.linear_model import LinearRegression
import pandas as pd
from sklearn.model_selection import train_test_split

df = pd.read_csv("./Summary of Weather.csv")

# extract the columns we need as dataFrame
X = df[["Precip"]]

# extract the column we need as series
y = df["MaxTemp"]
print(y)
# Create a linear regression model
model = LinearRegression()

model.fit(X, y)



print(model) 