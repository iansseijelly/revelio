package revelio

import chisel3._
import chisel3.util._

case class RevelioParams(
    // The size of matching block in pixels(bytes)
    blockSize: Int = 8,
    // The number of the functional units
    fuWidth: Int = 4,
    // The depth of the functional units (number of blocks computed per cycle)
    fuDepth: Int = 8,
    // The cost function used
    costFunct: String = "SAD",
    // The width of the target image
    imgWidth: Int = 450,
    // The height of the target image
    imgHeight: Int = 375,
    // Use SRAM for the row buffer
    useSRAM: Boolean = true,
){
    require(fuDepth % 4 == 0, "The depth of the functional units must be a multiple of 4")
    require(Seq("SAD", "SSD", "NCC").contains(costFunction), "The cost function must be one of SAD, SSD, NCC")
    require(imgWidth % 4 == 0, "The width of the image must be a multiple of 4bytes, 32 bits")
    def numBlocksPerIter: Int = fuDepth / blockSize
}