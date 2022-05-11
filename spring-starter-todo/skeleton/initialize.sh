mkdir -p .git/hooks
echo "Copying Pre-Commit hook"
cp ./pre-commit .git/hooks/
echo "Setting executable permissions to Pre-Commit hook."
chmod +x .git/hooks/pre-commit
