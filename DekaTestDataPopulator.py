import json
import requests
import os

# Load the JSON file
with open('testdata/deka/deka_testdata.json', 'r') as file:
    products = json.load(file)

# Base URL and API key
base_url = "https://api.dekamarkt.nl/v1/assortmentcache/283/"
api_key = "6d3a42a3-6d93-4f98-838d-bcc0ab2307fd"
query_params = "?api_key={}&filterAssortment=0".format(api_key)

# Output folder for saving the responses
output_folder = "testdata/deka/detail"
os.makedirs(output_folder, exist_ok=True)

# Fetch and save data for each ProductID
for product in products:
    product_id = product["ProductID"]
    url = f"{base_url}{product_id}{query_params}"

    try:
        # Make the API request
        response = requests.get(url)
        response.raise_for_status()  # Raise an exception for HTTP errors

        # Save the response to a file
        file_path = os.path.join(output_folder, f"{product_id}.json")
        with open(file_path, 'w') as output_file:
            json.dump(response.json(), output_file, indent=4)

        print(f"Data for ProductID {product_id} saved successfully.")
    except requests.exceptions.RequestException as e:
        print(f"Failed to fetch data for ProductID {product_id}: {e}")
