require 'rake'

require 'fileutils'

target_dir = 'bluej'

files_to_keep = ['README.TXT', 'package.bluej']


def keep_files?(path)
  file = File.basename path
  files_to_keep.include? file
end

def copy_file_keep_structure(file, trim)
  dir, filename = File.dirname(file), File.basename(file)
  dest = File.join(target_dir, dir)
  dest.gsub! trim, ''
  FileUtils.mkdir_p(dest)
  FileUtils.copy_file(file, File.join(dest,filename))
end

task :bluej, [:path_to_source] do |t, args|
  path = args[:path_to_source]
  puts "Path #{path}"
  Dir.glob("#{path}/**/*.java").each do |file|
    copy_file_keep_structure file, path
  end
end


task :clean do
  Dir["#{target_dir}/*"].each do |path|
    FileUtils.remove path
  end
end

task :copy do
  Dir['bluej-files/**/*'] do |file|
    copy_file_keep_structure file, ''
  end
end
