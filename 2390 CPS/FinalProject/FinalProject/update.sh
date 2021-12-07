sed -i - '1,9d' task1.asm
sed -i - '1,9d' task2.asm
sed -i - '1,9d' task3.asm
echo "$(cat README.txt)\n$(cat task1.asm)" > task1.asm
echo "$(cat README.txt)\n$(cat task2.asm)" > task2.asm
echo "$(cat README.txt)\n$(cat task3.asm)" > task3.asm
rm *.asm-
