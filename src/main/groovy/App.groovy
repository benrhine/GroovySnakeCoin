import com.benrhine.domain.GuavaBlock
import com.benrhine.domain.JavaBlock
import com.benrhine.service.SnakeCoinService

/** =================================================================================================================
 * Simple Blockchain implementation written in Groovy. Current block hashing choice makes use of Google's Guava
 * library but I have included a pure Java hashing implementation as well. If you wish to try the pure Java hashing
 * comment out createGuavaBlockChain in main and un-comment createJavaBlockChain.
 *
 * To Execute this application
 *      ./gradlew build
 *      ./gradlew run
 * ================================================================================================================== */
class App {
    private final static Integer numberOfBlocksToAdd = 20

    String getGreeting() {
        return "Welcome to Snake Coin. Now creating ${numberOfBlocksToAdd} SnakeCoins"
    }

    private createGuavaBlockChain(range) {
        SnakeCoinService snakeCoinService = new SnakeCoinService()
        List<GuavaBlock> blockChain = [snakeCoinService.createGenesisGuavaBlock()]
        GuavaBlock previousBlock = blockChain[0]

        println "Genesis Block has been added to the blockchain!\nGenesis Hash: ${blockChain[0].hash}\n"

        for (n in range) {
            final GuavaBlock newBlock = snakeCoinService.nextGuavaBlock(previousBlock)
            blockChain.add(newBlock)
            previousBlock = newBlock

            println "Block #${newBlock.index} has been added to the blockchain!"
            println "Hash: ${newBlock.hash}\n"
        }
    }

    private createJavaBlockChain(range) {
        SnakeCoinService snakeCoinService = new SnakeCoinService()
        List<JavaBlock> blockChain = [snakeCoinService.createGenesisJavaBlock()]
        JavaBlock previousBlock = blockChain[0]

        println "Genesis Block has been added to the blockchain!\nGenesis Hash: ${blockChain[0].hash}\n"

        for (n in range) {
            final JavaBlock newBlock = snakeCoinService.nextJavaBlock(previousBlock)
            blockChain.add(newBlock)
            previousBlock = newBlock

            println "Block #${newBlock.index} has been added to the blockchain!"
            println "Hash: ${newBlock.hash}\n"
        }
    }

    static void main(String[] args) {
        final range = 0..(numberOfBlocksToAdd-1)
        final App app = new App()
        println app.greeting

        /* Create block hashes using Guava library */
        app.createGuavaBlockChain(range)

        /* or */

        /* Create block hashes using pure Java (uncomment to use) */
        //app.createJavaBlockChain()
    }
}
