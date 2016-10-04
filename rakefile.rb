require 'rake'

require 'fileutils'

target_dir = 'bluej'

files_to_keep = ['README.TXT', 'package.bluej']


def keep_files?(path)
  file = File.basename path
  files_to_keep.include? file
end

def copy_file_keep_structure(file, target_dir, trim)
  dir, filename = File.dirname(file), File.basename(file)
  dest = File.join(target_dir, dir)
  dest.gsub! trim, ''
  FileUtils.mkdir_p(dest)
  FileUtils.copy_file(file, File.join(dest,filename))
end

task :bluej, [:path_to_source] => [:clean, :copy] do |t, args|
  path = args[:path_to_source]
  raise ArgumentError.new('path_to_source is required') unless path && path.strip != ''
  Dir.glob("#{path}/**/*").each do |file|
    copy_file_keep_structure file, target_dir, path
  end
end


task :clean do
  # FileUtils.rm Dir["#{target_dir}/*"]
  `rm -r #{target_dir}/*` if Dir["#{target_dir}/*"].size > 0
end

task :copy do
  Dir['bluej-files/**/*'].each do |file|
    copy_file_keep_structure file, target_dir, 'bluej-files'
  end
end
