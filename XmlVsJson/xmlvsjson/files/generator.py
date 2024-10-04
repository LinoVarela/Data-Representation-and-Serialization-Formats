def generate_school_data(num_students):
    school_data = []
    school_counter = 1
    student_counter = 1

    while student_counter <= num_students:
        school_data.append(f"School: School_{school_counter}")
        for i in range(10):  #each school has 10 students
            if student_counter <= num_students:
                school_data.append(f"{student_counter} Student_{student_counter}")
                student_counter += 1
        school_counter += 1
    
    return '\n'.join(school_data)

# Generate files for the requested number of students
students_counts = [100, 500, 1000, 2500, 5000, 7500, 10000, 15000]
file_contents = {f'schoolData_{count}.txt': generate_school_data(count) for count in students_counts}

# Saving files
file_paths = []
for file_name, content in file_contents.items():
    file_path = f'/mnt/data/{file_name}'
    with open(file_path, 'w') as file:
        file.write(content)
    file_paths.append(file_path)

file_paths
