package xpertss.ssh;

public enum FingerPrintFormat {

    Legacy {
        @Override
        public String generate(byte[] data)
        {
            // TODO Hex encoded MD5
            // MD5:f6:fc:1c:03:17:5f:67:4f:1f:0b:50:5a:9f:f9:30:e5
            return "";
        }
    },

    Modern {
        @Override
        public String generate(byte[] data)
        {
            // TODO Base64 encoded SHA-256
            // SHA256:7KMZvJiITZ+HbOyqjNPV5AeC5As2GSZES5baxy1NIe4
            return "";
        }
    };


    public abstract String generate(byte[] data);

}
