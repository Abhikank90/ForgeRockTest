# ForgeRockTest
ForgeRock Test - Rest API for extracting ML features

com.forgerock.test.controller package has Rest controller for API
com.forgerock.test.service package has service class with method for extracting ML features from JSON

Example POST API Json input :

{
    "featureConfig": {
        "id": 1,
        "name": "DeviceFeatures",
         "transforms": [
            {
                "name": "device_os",
                "useInML": true,
                "enabled": true,
                "jsltExpression": ".device.osType"
            },
            {
                "name": "device_description",
                "useInML": true,
                "enabled": true,
                "jsltExpression": ".device.osType + \" \" + .device.model"
            }
        ]
    },
    "inputJson": {
        "eventId": "878237843", 
        "device": {
            "osType": "Linux",
            "model": "Laptop"
        },
        "ip": "10.45.2.30",
        "sessionId": "ads79uoijd098098"
    }
}

Example output: 
{
    "eventId": "878237843",
    "device_description": "Linux Laptop",
    "device_os": "Linux"
}
