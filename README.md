# Wolsey-Tech HR Tracking Android Application

## Description

The purpose of this project is to provide Wolsey-Tech with a solid foundation for their mobile HR tracking application. This application has important functionalities that will need to be used by workers out in the oil fields and offline in order to access job specific data. These functionalities include a profile section, file center, and timesheet access. With these functionalities, we hope that Wolsey-Tech can provide access to their data when offline in most cases, and online whenever.

## Getting Started

### Dependencies

* Android Studio will be needed to access the Android simulators, download the application to an Android phone, and modify the code.
* The application was built using Gradle version 8.2, and Android Studio version 2023.1.1 Hedgehog although the Android Studio version shouldn't have too much effect now.
* Any libraries that have been used within Android Studio are located within the build.gradle.kts which is located in the "Gradle Scripts" section in Android Studio, and in the main directory of the project file.

### Installing

* Within Android Studio, to open the project you will simply have to clone this repository to some location of your preference, find the "Open Folder" option when selecting a new project, and select the "wolseyTechHR" folder to open. Note that opening the main file where the repository is stored likely will not work.
* After you have successfully opened the project in Android Studio, you might have to navigate the toolbar to File --> Sync Gradle Project Files to ensure that all files and dependencies have been loaded properly.

## Authors

Contributors names and contact info
Anthony Hani
ahani@ualberta.ca

Edan Hoover
edanjhoover09@gmail.com

Lukas Ottenhof
lottenho@ualberta.ca

Shadi Khalil
skhalil1@gmail.com

## Code Snippets for Unimplemented Features

### For HelpCenterActivity:

    private void sendQuestionToEmail(String question) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
    
            // Call the Firebase Cloud Function using Firebase Functions SDK
            FirebaseFunctions.getInstance()
                    .getHttpsCallable("sendQuestionEmail")
                    .call(getEmailData(userEmail, question))
                    .addOnSuccessListener(new OnSuccessListener<HttpsCallableResult>() {
                        @Override
                        public void onSuccess(HttpsCallableResult httpsCallableResult) {
                            // Log or display a confirmation message
                            System.out.println("Question submitted: " + question);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Handle the failure to send the email
                            System.out.println("Failed to send question email: " + e.getMessage());
                        }
                    });
        } else {
            // Handle the case when the user is not authenticated
            System.out.println("User not authenticated. Question not submitted.");
        }
    }

    private Map<String, Object> getEmailData(String userEmail, String question) {
        Map<String, Object> data = new HashMap<>();
        data.put("userEmail", userEmail);
        data.put("question", question);
        return data;
    }

### Firebase Project (node.js):

    const functions = require('firebase-functions');
    const admin = require('firebase-admin');
    const nodemailer = require('nodemailer');
    
    admin.initializeApp();
    
    exports.sendQuestionEmail = functions.https.onCall(async (data, context) => {
        const userEmail = data.userEmail;
        const question = data.question;
    
        // Implement your email sending logic using nodemailer or other libraries
        const transporter = nodemailer.createTransport({
            service: 'gmail',
            auth: {
                user: 'your-email@gmail.com',
                pass: 'your-email-password'
            }
        });
    
        const mailOptions = {
            from: 'your-email@gmail.com',
            to: userEmail,
            subject: 'Question Submitted',
            text: `Your question: ${question}`
        };
    
        await transporter.sendMail(mailOptions);
    
        return { success: true, message: 'Email sent successfully' };
    });

 ### LoginEncryption code: 

    <?php
    
    /**
     * Class LoginEncryption
     *
     * This class provides methods for encrypting and decrypting data using AES in ECB mode and RSA algorithms.
     */
    class LoginEncryption {
    
        // Private properties to store keys
        private $secretKey;
        private $privateKey;
        private $publicKey;
    
        /**
         * Constructor for the LoginEncryption class.
         *
         * @param string|null $secretKey   The secret key for AES encryption. If not provided, a random key will be generated.
         * @param string|null $privateKey  The private key for RSA encryption. If not provided, a key pair will be generated.
         * @param string|null $publicKey   The public key for RSA encryption. If not provided, a key pair will be generated.
         */
        public function __construct($secretKey = null, $privateKey = null, $publicKey = null) {
            $this->secretKey = $secretKey ?: self::generateAESKey();
            $keyPair = $privateKey && $publicKey ? ['privateKey' => $privateKey, 'publicKey' => $publicKey] : self::generateRSAKeyPair();
            $this->privateKey = $keyPair['privateKey'];
            $this->publicKey = $keyPair['publicKey'];
        }
    
        /**
         * Get the secret key used for AES encryption.
         *
         * @return string The secret key.
         */
        public function getSecretKey() {
            return $this->secretKey;
        }
    
        /**
         * Get the private key used for RSA encryption.
         *
         * @return string The private key.
         */
        public function getPrivateKey() {
            return $this->privateKey;
        }
    
        /**
         * Get the public key used for RSA encryption.
         *
         * @return string The public key.
         */
        public function getPublicKey() {
            return $this->publicKey;
        }
    
        /**
         * Encrypt the given plain text using AES in ECB mode and RSA.
         *
         * @param string $plainText The plain text to encrypt.
         * @return string The encrypted data.
         */
        public function encrypt($plainText) {
            $encryptedAES = self::encryptAES($plainText, $this->secretKey);
            openssl_public_encrypt($encryptedAES, $encryptedData, $this->publicKey);
            return $encryptedData;
        }
    
        /**
         * Decrypt the given encrypted data using RSA and AES in ECB mode.
         *
         * @param string $encryptedData The encrypted data to decrypt.
         * @return string The decrypted plain text.
         */
        public function decrypt($encryptedData) {
            openssl_private_decrypt($encryptedData, $decryptedAES, $this->privateKey);
            return self::decryptAES($decryptedAES, $this->secretKey);
        }
    
        /**
         * Generate a random AES key.
         *
         * @return string The generated AES key.
         */
        private static function generateAESKey() {
            $keyGen = openssl_cipher_iv_length("AES-256-ECB");
            return bin2hex(random_bytes($keyGen));
        }
    
        /**
         * Generate a key pair for RSA encryption.
         *
         * @return array An array containing the private and public keys.
         */
        private static function generateRSAKeyPair() {
            $config = [
                "digest_alg" => "sha512",
                "private_key_bits" => 2048,
                "private_key_type" => OPENSSL_KEYTYPE_RSA,
            ];
            $res = openssl_pkey_new($config);
            openssl_pkey_export($res, $privateKey);
            $publicKey = openssl_pkey_get_details($res)["key"];
            return ["privateKey" => $privateKey, "publicKey" => $publicKey];
        }
    
        /**
         * Encrypt the given plain text using AES in ECB mode.
         *
         * @param string $plainText The plain text to encrypt.
         * @param string $secretKey The secret key for encryption.
         * @return string The encrypted data.
         */
        private static function encryptAES($plainText, $secretKey) {
            $encryptedBytes = openssl_encrypt($plainText, "AES-256-ECB", $secretKey, 0);
            return base64_encode($encryptedBytes);
        }
    
        /**
         * Decrypt the given encrypted text using AES in ECB mode.
         *
         * @param string $encryptedText The encrypted text to decrypt.
         * @param string $secretKey The secret key for decryption.
         * @return string The decrypted plain text.
         */
        private static function decryptAES($encryptedText, $secretKey) {
            $encryptedBytes = base64_decode($encryptedText);
            $decryptedText = openssl_decrypt($encryptedBytes, "AES-256-ECB", $secretKey, 0);
            return $decryptedText;
        }
    }
    
    // DRIVER SCRIPT
    $dudeEncryptor = new LoginEncryption();

    // Sample data
    $originalText = "Wow, this is some top-secret message, dude! 🤐";
    
    // Encrypt the data
    $encryptedData = $dudeEncryptor->encrypt($originalText);
    echo "Encrypted message: $encryptedData\n";
    
    // Decrypt the data
    $decryptedText = $dudeEncryptor->decrypt($encryptedData);
    echo "Decrypted message: $decryptedText\n";

