package array;
//Understanding builder design technique
public class BankAccount {

    private String name;
    private String accountNumber;
    private String email;
    private boolean newsletter;

    @Override
    public String toString() {
        return "BankAccount{" +
                "name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", email='" + email + '\'' +
                ", newsletter=" + newsletter +
                '}';
    }

    public BankAccount(BankAccountBuilder bankAccountBuilder) {
        this.name=bankAccountBuilder.name;
        this.newsletter=bankAccountBuilder.newsletter;
        this.email=bankAccountBuilder.email;
        this.accountNumber=bankAccountBuilder.accountNumber;

    }

    // constructors/getters

    public static class BankAccountBuilder {

        private String name;
        private String accountNumber;
        private String email;
        private boolean newsletter;

        public BankAccountBuilder(String name, String accountNumber) {
            this.name = name;
            this.accountNumber = accountNumber;
        }

        public BankAccountBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public BankAccountBuilder wantNewsletter(boolean newsletter) {
            this.newsletter = newsletter;
            return this;
        }

        public BankAccount build() {
            return new BankAccount(this);
        }
    }
    public static void main (String args[]){
        BankAccount newAccount = new BankAccount
                .BankAccountBuilder("Jon", "22738022275")
                .withEmail("jon@example.com")
//                .wantNewsletter(true)
                .build();
        System.out.println(newAccount.toString());
    }
}

