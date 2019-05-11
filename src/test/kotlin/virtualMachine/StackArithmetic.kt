package virtualMachine

import org.junit.Assert
import org.junit.Ignore
import virtualMachine.ProcessVirtualMachineFile.getInstructionFromRawInput
import virtualMachine.stack.datawrappers.Word
import virtualMachine.stack.datawrappers.instruction.Instruction
import virtualMachine.stack.vm_instruction_parsing.VmParser
import kotlin.test.Test

class StackArithmetic {

    @Test
    fun testLoadConstants() {
        val line = getInstructionFromRawInput("push constant 2")
        val vmParser = VmParser(listOf(line))

        vmParser.executeVmInstructions()
        val data = vmParser.virtualMemory.popStack()
        Assert.assertEquals(2, data.convertToInteger())
    }

    @Test
    fun testSimpleAddition() {
        val instructions: List<Instruction> = listOf(
                getInstructionFromRawInput("push constant 2"),
                getInstructionFromRawInput("push constant 7"),
                getInstructionFromRawInput("add")
        )

        val vmParser = VmParser(instructions)
        vmParser.executeVmInstructions()

        val result: Word = vmParser.virtualMemory.popStack()
        Assert.assertEquals(9, result.convertToInteger())
    }

    @Test
    fun testMoreComplexAddition() {
        val instructions: List<Instruction> = listOf(
                getInstructionFromRawInput("push constant 2"),
                getInstructionFromRawInput("push constant 7"),
                getInstructionFromRawInput("add"),
                getInstructionFromRawInput("push constant 2"),
                getInstructionFromRawInput("add")
        )

        val vmParser = VmParser(instructions)
        vmParser.executeVmInstructions()

        val result: Word = vmParser.virtualMemory.popStack()
        Assert.assertEquals(11, result.convertToInteger())
    }

    @Test
    fun testExampleWithLogicalInputs() {
        val instructions: List<Instruction> = listOf(
                getInstructionFromRawInput("push constant 2"),
                getInstructionFromRawInput("push constant 7"),
                getInstructionFromRawInput("add"),
                getInstructionFromRawInput("push constant 2"),
                getInstructionFromRawInput("gt")
        )

        val vmParser = VmParser(instructions)
        vmParser.executeVmInstructions()

        val result: Word = vmParser.virtualMemory.popStack()

        val trueSixteenBit = Word(true)
        Assert.assertEquals(trueSixteenBit, result)
    }

    @Test
    @Ignore
    fun testLoadFile() {
        val vmParser: VmParser = ProcessVirtualMachineFile().processVmFile("./src/test/StackArithmetic/StackTest/StackTest.vm", null)
        val result: Word = vmParser.virtualMemory.popStack()
        //TODO check this test
        Assert.assertEquals(32685, result.convertToInteger())
    }

}
